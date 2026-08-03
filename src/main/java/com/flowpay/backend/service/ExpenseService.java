package com.flowpay.backend.service;

import com.flowpay.backend.dto.BalanceResponse;
import com.flowpay.backend.dto.CreateExpenseRequest;
import com.flowpay.backend.dto.ExpenseResponse;
import com.flowpay.backend.dto.SettlementResponse;
import com.flowpay.backend.dto.UserBalance;
import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.entity.ExpenseGroup;
import com.flowpay.backend.entity.ExpenseParticipant;
import com.flowpay.backend.entity.User;
import com.flowpay.backend.repository.ExpenseGroupRepository;
import com.flowpay.backend.repository.ExpenseParticipantRepository;
import com.flowpay.backend.repository.ExpenseRepository;
import com.flowpay.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseGroupRepository expenseGroupRepository;
    private final UserRepository userRepository;
    private final ExpenseParticipantRepository expenseParticipantRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            ExpenseGroupRepository expenseGroupRepository,
            UserRepository userRepository,
            ExpenseParticipantRepository expenseParticipantRepository) {

        this.expenseRepository = expenseRepository;
        this.expenseGroupRepository = expenseGroupRepository;
        this.userRepository = userRepository;
        this.expenseParticipantRepository = expenseParticipantRepository;
    }

    @Transactional
    public ExpenseResponse createExpense(
            Long groupId,
            CreateExpenseRequest request) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        User user = userRepository.findById(request.getPaidByUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Expense expense = new Expense();

        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setPaidBy(user);
        expense.setExpenseGroup(group);

        Expense savedExpense = expenseRepository.save(expense);

        for (Long participantId : request.getParticipantIds()) {

            User participant = userRepository.findById(participantId)
                    .orElseThrow(() ->
                            new RuntimeException("Participant not found"));

            ExpenseParticipant expenseParticipant = new ExpenseParticipant();

            expenseParticipant.setExpense(savedExpense);
            expenseParticipant.setUser(participant);

            expenseParticipantRepository.save(expenseParticipant);
        }

        return new ExpenseResponse(
                savedExpense.getId(),
                savedExpense.getTitle(),
                savedExpense.getAmount(),
                savedExpense.getPaidBy().getName(),
                savedExpense.getCreatedAt()
        );
    }

    public List<ExpenseResponse> getGroupExpenses(Long groupId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        return expenseRepository.findByExpenseGroup(group)
                .stream()
                .map(expense -> new ExpenseResponse(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getPaidBy().getName(),
                        expense.getCreatedAt()
                ))
                .toList();
    }

    public List<BalanceResponse> calculateBalances(Long groupId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        List<Expense> expenses =
                expenseRepository.findByExpenseGroup(group);

        Map<Long, BigDecimal> balances = new HashMap<>();
        Map<Long, String> names = new HashMap<>();

        for (Expense expense : expenses) {

            List<ExpenseParticipant> participants =
                    expenseParticipantRepository.findByExpense(expense);

            if (participants.isEmpty()) {
                continue;
            }

            BigDecimal share = expense.getAmount()
                    .divide(
                            BigDecimal.valueOf(participants.size()),
                            2,
                            RoundingMode.HALF_UP
                    );

            for (ExpenseParticipant participant : participants) {

                Long userId = participant.getUser().getId();

                names.put(userId, participant.getUser().getName());

                balances.put(
                        userId,
                        balances.getOrDefault(userId, BigDecimal.ZERO)
                                .subtract(share)
                );
            }

            Long paidUserId = expense.getPaidBy().getId();

            names.put(paidUserId, expense.getPaidBy().getName());

            balances.put(
                    paidUserId,
                    balances.getOrDefault(paidUserId, BigDecimal.ZERO)
                            .add(expense.getAmount())
            );
        }

        return balances.entrySet()
                .stream()
                .map(entry -> new BalanceResponse(
                        entry.getKey(),
                        names.get(entry.getKey()),
                        entry.getValue()
                ))
                .toList();
    }

    private List<UserBalance> getUserBalances(Long groupId) {

        return new ArrayList<>(
                calculateBalances(groupId)
                        .stream()
                        .map(balance -> new UserBalance(
                                balance.getUserId(),
                                balance.getUserName(),
                                balance.getBalance()
                        ))
                        .toList()
        );
    }

    public List<SettlementResponse> calculateSettlements(Long groupId) {

        List<UserBalance> balances = getUserBalances(groupId);

        List<UserBalance> creditors = new ArrayList<>(
                balances.stream()
                        .filter(b -> b.getBalance().compareTo(BigDecimal.ZERO) > 0)
                        .toList()
        );

        List<UserBalance> debtors = new ArrayList<>(
                balances.stream()
                        .filter(b -> b.getBalance().compareTo(BigDecimal.ZERO) < 0)
                        .toList()
        );

        List<SettlementResponse> settlements = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < debtors.size() && j < creditors.size()) {

            UserBalance debtor = debtors.get(i);
            UserBalance creditor = creditors.get(j);

            BigDecimal debt = debtor.getBalance().abs();
            BigDecimal credit = creditor.getBalance();

            BigDecimal amount = debt.min(credit);

            settlements.add(
                    new SettlementResponse(
                            debtor.getUserName(),
                            creditor.getUserName(),
                            amount
                    )
            );

            debtor.setBalance(
                    debtor.getBalance().add(amount)
            );

            creditor.setBalance(
                    creditor.getBalance().subtract(amount)
            );

            if (debtor.getBalance().compareTo(BigDecimal.ZERO) == 0) {
                i++;
            }

            if (creditor.getBalance().compareTo(BigDecimal.ZERO) == 0) {
                j++;
            }
        }

        return settlements;
    }
}
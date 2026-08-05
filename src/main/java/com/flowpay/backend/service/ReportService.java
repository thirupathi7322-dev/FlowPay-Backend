package com.flowpay.backend.service;

import com.flowpay.backend.dto.MonthlyReportResponse;
import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.entity.ExpenseGroup;
import com.flowpay.backend.repository.ExpenseGroupRepository;
import com.flowpay.backend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import com.flowpay.backend.dto.UserReportResponse;
import com.flowpay.backend.entity.ExpenseParticipant;
import com.flowpay.backend.entity.User;
import com.flowpay.backend.repository.ExpenseParticipantRepository;
import com.flowpay.backend.repository.UserRepository;

import java.math.RoundingMode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class ReportService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseGroupRepository expenseGroupRepository;
    private final UserRepository userRepository;
    private final ExpenseParticipantRepository expenseParticipantRepository;

    public ReportService(
            ExpenseRepository expenseRepository,
            ExpenseGroupRepository expenseGroupRepository,
            UserRepository userRepository,
            ExpenseParticipantRepository expenseParticipantRepository) {

        this.expenseRepository = expenseRepository;
        this.expenseGroupRepository = expenseGroupRepository;
        this.userRepository = userRepository;
        this.expenseParticipantRepository = expenseParticipantRepository;
    }
    public MonthlyReportResponse getMonthlyReport(Long groupId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        List<Expense> expenses =
                expenseRepository.findByExpenseGroup(group);

        BigDecimal totalExpenses = BigDecimal.ZERO;

        long totalTransactions = 0;

        YearMonth currentMonth = YearMonth.now();

        for (Expense expense : expenses) {

            YearMonth expenseMonth =
                    YearMonth.from(expense.getCreatedAt());

            if (expenseMonth.equals(currentMonth)) {

                totalExpenses =
                        totalExpenses.add(expense.getAmount());

                totalTransactions++;
            }
        }

        BigDecimal averageExpense = BigDecimal.ZERO;

        if (totalTransactions > 0) {

            averageExpense = totalExpenses.divide(
                    BigDecimal.valueOf(totalTransactions),
                    2,
                    RoundingMode.HALF_UP
            );
        }

        String month =
                currentMonth.getMonth()
                        .getDisplayName(
                                TextStyle.FULL,
                                Locale.ENGLISH
                        )
                        + " " +
                        currentMonth.getYear();

        return new MonthlyReportResponse(
                month,
                totalExpenses,
                totalTransactions,
                averageExpense
        );
    }
    public UserReportResponse getUserReport(Long groupId, Long userId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Expense> expenses = expenseRepository.findByExpenseGroup(group);

        BigDecimal totalPaid = BigDecimal.ZERO;
        int totalExpenses = 0;
        int totalParticipated = 0;

        for (Expense expense : expenses) {

            if (expense.getPaidBy().getId().equals(userId)) {
                totalPaid = totalPaid.add(expense.getAmount());
                totalExpenses++;
            }

            List<ExpenseParticipant> participants =
                    expenseParticipantRepository.findByExpense(expense);

            boolean participated = participants.stream()
                    .anyMatch(p -> p.getUser().getId().equals(userId));

            if (participated) {
                totalParticipated++;
            }
        }

        BigDecimal averageExpense = BigDecimal.ZERO;

        if (totalExpenses > 0) {
            averageExpense = totalPaid.divide(
                    BigDecimal.valueOf(totalExpenses),
                    2,
                    RoundingMode.HALF_UP
            );
        }

        return new UserReportResponse(
                user.getName(),
                totalPaid,
                totalExpenses,
                totalParticipated,
                averageExpense
        );
    }

}
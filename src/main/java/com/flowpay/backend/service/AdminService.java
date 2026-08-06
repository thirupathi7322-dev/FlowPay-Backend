package com.flowpay.backend.service;

import com.flowpay.backend.dto.AdminGroupResponse;
import com.flowpay.backend.dto.AdminStatsResponse;
import com.flowpay.backend.dto.AdminUserResponse;
import com.flowpay.backend.repository.BudgetRepository;
import com.flowpay.backend.repository.ExpenseGroupRepository;
import com.flowpay.backend.repository.ExpenseRepository;
import com.flowpay.backend.repository.GroupMemberRepository;
import com.flowpay.backend.repository.ReceiptRepository;
import com.flowpay.backend.repository.RecurringExpenseRepository;
import com.flowpay.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.flowpay.backend.dto.AdminExpenseResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final UserRepository userRepository;
    private final ExpenseGroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final ReceiptRepository receiptRepository;
    private final BudgetRepository budgetRepository;
    private final RecurringExpenseRepository recurringExpenseRepository;
    private final GroupMemberRepository groupMemberRepository;

    public AdminService(
            UserRepository userRepository,
            ExpenseGroupRepository groupRepository,
            ExpenseRepository expenseRepository,
            ReceiptRepository receiptRepository,
            BudgetRepository budgetRepository,
            RecurringExpenseRepository recurringExpenseRepository,
            GroupMemberRepository groupMemberRepository) {

        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.receiptRepository = receiptRepository;
        this.budgetRepository = budgetRepository;
        this.recurringExpenseRepository = recurringExpenseRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    public AdminStatsResponse getStats() {

        return new AdminStatsResponse(
                userRepository.count(),
                groupRepository.count(),
                expenseRepository.count(),
                receiptRepository.count(),
                budgetRepository.count(),
                recurringExpenseRepository.count()
        );
    }

    public List<AdminUserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> new AdminUserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    public List<AdminGroupResponse> getAllGroups() {

        return groupRepository.findAll()
                .stream()
                .map(group -> new AdminGroupResponse(
                        group.getId(),
                        group.getName(),
                        group.getDescription(),
                        groupMemberRepository.countByExpenseGroup(group)
                ))
                .collect(Collectors.toList());
    }
    public List<AdminExpenseResponse> getAllExpenses() {

        return expenseRepository.findAll()
                .stream()
                .map(expense -> new AdminExpenseResponse(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getCategory().getName(),
                        expense.getPaidBy().getName(),
                        expense.getExpenseGroup().getName(),
                        expense.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }
    public List<AdminUserResponse> searchUsers(String name) {

        return userRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(user -> new AdminUserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }
    public List<AdminGroupResponse> searchGroups(String name) {

        return groupRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(group -> new AdminGroupResponse(
                        group.getId(),
                        group.getName(),
                        group.getDescription(),
                        groupMemberRepository.countByExpenseGroup(group)
                ))
                .collect(Collectors.toList());
    }
}
package com.flowpay.backend.service;

import com.flowpay.backend.dto.DashboardResponse;
import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.entity.ExpenseGroup;
import com.flowpay.backend.repository.ExpenseGroupRepository;
import com.flowpay.backend.repository.ExpenseRepository;
import com.flowpay.backend.repository.GroupMemberRepository;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.HashMap;
import com.flowpay.backend.dto.CategoryAnalyticsResponse;
import com.flowpay.backend.dto.UserAnalyticsResponse;
import java.util.HashMap;
import java.util.Map;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DashboardService {

    private final ExpenseGroupRepository expenseGroupRepository;
    private final ExpenseRepository expenseRepository;
    private final GroupMemberRepository groupMemberRepository;

    public DashboardService(
            ExpenseGroupRepository expenseGroupRepository,
            ExpenseRepository expenseRepository,
            GroupMemberRepository groupMemberRepository) {

        this.expenseGroupRepository = expenseGroupRepository;
        this.expenseRepository = expenseRepository;
        this.groupMemberRepository = groupMemberRepository;
    }

    public DashboardResponse getDashboard(Long groupId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        List<Expense> expenses =
                expenseRepository.findByExpenseGroup(group);

        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Expense expense : expenses) {
            totalExpense = totalExpense.add(expense.getAmount());
        }

        long totalMembers =
                groupMemberRepository.countByExpenseGroup(group);

        long totalTransactions =
                expenseRepository.countByExpenseGroup(group);

        return new DashboardResponse(
                group.getName(),
                totalExpense,
                (int) totalMembers,
                (int) totalTransactions
        );
    }
    public List<CategoryAnalyticsResponse> getCategoryAnalytics(Long groupId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        List<Expense> expenses =
                expenseRepository.findByExpenseGroup(group);

        Map<String, BigDecimal> analytics = new HashMap<>();

        for (Expense expense : expenses) {

            String category =
                    expense.getCategory().getName();

            analytics.put(
                    category,
                    analytics.getOrDefault(category, BigDecimal.ZERO)
                            .add(expense.getAmount())
            );
        }

        return analytics.entrySet()
                .stream()
                .map(entry -> new CategoryAnalyticsResponse(
                        entry.getKey(),
                        entry.getValue()
                ))
                .toList();
    }
    public List<UserAnalyticsResponse> getUserAnalytics(Long groupId) {

        ExpenseGroup group = expenseGroupRepository.findById(groupId)
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        List<Expense> expenses =
                expenseRepository.findByExpenseGroup(group);

        Map<String, BigDecimal> analytics = new HashMap<>();

        for (Expense expense : expenses) {

            String userName =
                    expense.getPaidBy().getName();

            analytics.put(
                    userName,
                    analytics.getOrDefault(userName, BigDecimal.ZERO)
                            .add(expense.getAmount())
            );
        }

        return analytics.entrySet()
                .stream()
                .map(entry -> new UserAnalyticsResponse(
                        entry.getKey(),
                        entry.getValue()
                ))
                .toList();
    }
}
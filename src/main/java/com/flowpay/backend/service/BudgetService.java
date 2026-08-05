package com.flowpay.backend.service;

import com.flowpay.backend.dto.BudgetRequest;
import com.flowpay.backend.dto.BudgetResponse;
import com.flowpay.backend.dto.BudgetUsageResponse;
import com.flowpay.backend.entity.Budget;
import com.flowpay.backend.entity.Category;
import com.flowpay.backend.entity.User;
import com.flowpay.backend.repository.BudgetRepository;
import com.flowpay.backend.repository.CategoryRepository;
import com.flowpay.backend.repository.ExpenseRepository;
import com.flowpay.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.flowpay.backend.dto.BudgetDashboardResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;

    public BudgetService(
            BudgetRepository budgetRepository,
            UserRepository userRepository,
            CategoryRepository categoryRepository,
            ExpenseRepository expenseRepository) {

        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.expenseRepository = expenseRepository;
    }

    public BudgetResponse createBudget(BudgetRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        budgetRepository.findByUserIdAndCategoryIdAndMonth(
                request.getUserId(),
                request.getCategoryId(),
                request.getMonth()
        ).ifPresent(budget -> {
            throw new RuntimeException(
                    "Budget already exists for this month and category."
            );
        });

        Budget budget = new Budget();

        budget.setMonth(request.getMonth());
        budget.setBudgetAmount(request.getBudgetAmount());
        budget.setUser(user);
        budget.setCategory(category);

        Budget saved = budgetRepository.save(budget);

        return new BudgetResponse(
                saved.getId(),
                saved.getMonth(),
                saved.getBudgetAmount(),
                saved.getCategory().getName(),
                saved.getUser().getName()
        );
    }

    public List<BudgetResponse> getBudgetsByUser(Long userId) {

        return budgetRepository.findByUserId(userId)
                .stream()
                .map(budget -> new BudgetResponse(
                        budget.getId(),
                        budget.getMonth(),
                        budget.getBudgetAmount(),
                        budget.getCategory().getName(),
                        budget.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public BudgetUsageResponse getBudgetUsage(
            Long userId,
            Long categoryId,
            String month) {

        Budget budget = budgetRepository
                .findByUserIdAndCategoryIdAndMonth(
                        userId,
                        categoryId,
                        month)
                .orElseThrow(() ->
                        new RuntimeException("Budget not found"));

        BigDecimal spent =
                expenseRepository.getTotalSpent(
                        userId,
                        categoryId,
                        month);

        BigDecimal remaining =
                budget.getBudgetAmount().subtract(spent);

        double percentage =
                spent.doubleValue() * 100
                        / budget.getBudgetAmount().doubleValue();

        return new BudgetUsageResponse(
                month,
                budget.getCategory().getName(),
                budget.getBudgetAmount(),
                spent,
                remaining,
                percentage
        );
    }
    public BudgetDashboardResponse getBudgetDashboard(
            Long userId,
            Long categoryId,
            String month) {

        BudgetUsageResponse usage =
                getBudgetUsage(userId, categoryId, month);

        return new BudgetDashboardResponse(
                usage.getCategory(),
                usage.getBudget(),
                usage.getSpent(),
                usage.getRemaining(),
                usage.getUsagePercentage()
        );
    }
}
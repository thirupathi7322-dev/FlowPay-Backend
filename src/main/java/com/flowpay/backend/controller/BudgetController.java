package com.flowpay.backend.controller;

import com.flowpay.backend.dto.BudgetRequest;
import com.flowpay.backend.dto.BudgetResponse;
import com.flowpay.backend.dto.BudgetUsageResponse;
import com.flowpay.backend.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.flowpay.backend.dto.BudgetDashboardResponse;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public BudgetResponse createBudget(
            @Valid @RequestBody BudgetRequest request) {

        return budgetService.createBudget(request);
    }

    @GetMapping("/user/{userId}")
    public List<BudgetResponse> getBudgetsByUser(
            @PathVariable Long userId) {

        return budgetService.getBudgetsByUser(userId);
    }
    @GetMapping("/usage")
    public BudgetUsageResponse getBudgetUsage(

            @RequestParam Long userId,
            @RequestParam Long categoryId,
            @RequestParam String month) {

        return budgetService.getBudgetUsage(
                userId,
                categoryId,
                month);
    }
    @GetMapping("/dashboard")
    public BudgetDashboardResponse getBudgetDashboard(
            @RequestParam Long userId,
            @RequestParam Long categoryId,
            @RequestParam String month) {

        return budgetService.getBudgetDashboard(
                userId,
                categoryId,
                month
        );
    }
}
package com.flowpay.backend.controller;

import com.flowpay.backend.dto.CreateExpenseRequest;
import com.flowpay.backend.dto.ExpenseResponse;
import com.flowpay.backend.dto.SettlementResponse;
import com.flowpay.backend.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.flowpay.backend.dto.BalanceResponse;

import java.util.List;

@RestController
@RequestMapping("/api/groups/{groupId}/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseResponse createExpense(
            @PathVariable Long groupId,
            @Valid @RequestBody CreateExpenseRequest request) {

        return expenseService.createExpense(groupId, request);
    }

    @GetMapping
    public List<ExpenseResponse> getGroupExpenses(
            @PathVariable Long groupId) {

        return expenseService.getGroupExpenses(groupId);
    }
    @GetMapping("/balances")
    public List<BalanceResponse> getBalances(
            @PathVariable Long groupId) {

        return expenseService.calculateBalances(groupId);
    }
    @GetMapping("/settlements")
    public List<SettlementResponse> getSettlements(
            @PathVariable Long groupId) {
        System.out.println("===== SETTLEMENT API HIT =====");

        return expenseService.calculateSettlements(groupId);
    }
}
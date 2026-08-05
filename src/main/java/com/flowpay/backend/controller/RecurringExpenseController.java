package com.flowpay.backend.controller;

import com.flowpay.backend.dto.RecurringExpenseRequest;
import com.flowpay.backend.dto.RecurringExpenseResponse;
import com.flowpay.backend.service.RecurringExpenseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recurring-expenses")
public class RecurringExpenseController {

    private final RecurringExpenseService recurringExpenseService;

    public RecurringExpenseController(
            RecurringExpenseService recurringExpenseService) {

        this.recurringExpenseService = recurringExpenseService;
    }

    @PostMapping
    public RecurringExpenseResponse createRecurringExpense(
            @Valid @RequestBody RecurringExpenseRequest request) {

        return recurringExpenseService
                .createRecurringExpense(request);
    }

    @GetMapping
    public List<RecurringExpenseResponse> getAllRecurringExpenses() {

        return recurringExpenseService
                .getAllRecurringExpenses();
    }
}
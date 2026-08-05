package com.flowpay.backend.service;

import com.flowpay.backend.dto.RecurringExpenseRequest;
import com.flowpay.backend.dto.RecurringExpenseResponse;
import com.flowpay.backend.entity.Category;
import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.entity.ExpenseGroup;
import com.flowpay.backend.entity.RecurringExpense;
import com.flowpay.backend.entity.User;
import com.flowpay.backend.repository.CategoryRepository;
import com.flowpay.backend.repository.ExpenseGroupRepository;
import com.flowpay.backend.repository.ExpenseRepository;
import com.flowpay.backend.repository.RecurringExpenseRepository;
import com.flowpay.backend.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecurringExpenseService {

    private final RecurringExpenseRepository recurringExpenseRepository;
    private final ExpenseGroupRepository expenseGroupRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;
    private final CategoryRepository categoryRepository;

    public RecurringExpenseService(
            RecurringExpenseRepository recurringExpenseRepository,
            ExpenseGroupRepository expenseGroupRepository,
            UserRepository userRepository,
            ExpenseRepository expenseRepository,
            CategoryRepository categoryRepository) {

        this.recurringExpenseRepository = recurringExpenseRepository;
        this.expenseGroupRepository = expenseGroupRepository;
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.categoryRepository = categoryRepository;
    }

    public RecurringExpenseResponse createRecurringExpense(
            RecurringExpenseRequest request) {

        ExpenseGroup group = expenseGroupRepository
                .findById(request.getGroupId())
                .orElseThrow(() ->
                        new RuntimeException("Group not found"));

        User paidBy = userRepository
                .findById(request.getPaidById())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        RecurringExpense recurringExpense = new RecurringExpense();

        recurringExpense.setTitle(request.getTitle());
        recurringExpense.setAmount(request.getAmount());
        recurringExpense.setFrequency(request.getFrequency());
        recurringExpense.setNextRun(request.getNextRun());
        recurringExpense.setExpenseGroup(group);
        recurringExpense.setPaidBy(paidBy);
        recurringExpense.setCategory(category);
        recurringExpense.setActive(true);

        RecurringExpense saved =
                recurringExpenseRepository.save(recurringExpense);

        return new RecurringExpenseResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getAmount(),
                saved.getFrequency(),
                saved.getNextRun(),
                saved.isActive()
        );
    }

    public List<RecurringExpenseResponse> getAllRecurringExpenses() {

        return recurringExpenseRepository.findAll()
                .stream()
                .map(expense -> new RecurringExpenseResponse(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getFrequency(),
                        expense.getNextRun(),
                        expense.isActive()
                ))
                .collect(Collectors.toList());
    }

    public void processRecurringExpenses() {

        List<RecurringExpense> recurringExpenses =
                recurringExpenseRepository
                        .findByActiveTrueAndNextRun(LocalDate.now());

        for (RecurringExpense recurring : recurringExpenses) {

            Expense expense = new Expense();

            expense.setTitle(recurring.getTitle());
            expense.setAmount(recurring.getAmount());
            expense.setExpenseGroup(recurring.getExpenseGroup());
            expense.setPaidBy(recurring.getPaidBy());
            expense.setCategory(recurring.getCategory());

            expenseRepository.save(expense);

            switch (recurring.getFrequency()) {

                case "DAILY":
                    recurring.setNextRun(
                            recurring.getNextRun().plusDays(1));
                    break;

                case "WEEKLY":
                    recurring.setNextRun(
                            recurring.getNextRun().plusWeeks(1));
                    break;

                case "MONTHLY":
                    recurring.setNextRun(
                            recurring.getNextRun().plusMonths(1));
                    break;

                case "YEARLY":
                    recurring.setNextRun(
                            recurring.getNextRun().plusYears(1));
                    break;
            }

            recurringExpenseRepository.save(recurring);

            System.out.println(
                    "Created recurring expense: "
                            + recurring.getTitle()
            );
        }
    }
}
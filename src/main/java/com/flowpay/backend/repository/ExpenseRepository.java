package com.flowpay.backend.repository;

import com.flowpay.backend.entity.Category;
import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.entity.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByExpenseGroup(ExpenseGroup expenseGroup);

    List<Expense> findByExpenseGroupAndCategory(
            ExpenseGroup expenseGroup,
            Category category
    );
}
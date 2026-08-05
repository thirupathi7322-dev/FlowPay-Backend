package com.flowpay.backend.repository;

import com.flowpay.backend.entity.Category;
import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.entity.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.math.BigDecimal;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {


    List<Expense> findByExpenseGroup(ExpenseGroup expenseGroup);
    long countByExpenseGroup(ExpenseGroup expenseGroup);

    List<Expense> findByExpenseGroupAndCategory(
            ExpenseGroup expenseGroup,
            Category category
    );
    @Query("""
SELECT COALESCE(SUM(e.amount), 0)
FROM Expense e
WHERE e.paidBy.id = :userId
AND e.category.id = :categoryId
AND FUNCTION('DATE_FORMAT', e.createdAt, '%Y-%m') = :month
""")
    BigDecimal getTotalSpent(
            Long userId,
            Long categoryId,
            String month
    );
}
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
    Expense findFirstByPaidByIdOrderByAmountDesc(Long userId);

    List<Expense> findByPaidById(Long userId);

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
    @Query("""
SELECT COALESCE(SUM(e.amount), 0)
FROM Expense e
WHERE e.paidBy.id = :userId
""")
    BigDecimal getTotalSpentByUser(Long userId);
    @Query("""
SELECT c.name, SUM(e.amount)
FROM Expense e
JOIN e.category c
WHERE e.paidBy.id = :userId
GROUP BY c.id, c.name
ORDER BY SUM(e.amount) DESC
""")
    List<Object[]> getCategoryTotals(Long userId);
    @Query("""
SELECT COALESCE(SUM(e.amount),0)
FROM Expense e
WHERE e.paidBy.id = :userId
AND e.category.name = :category
""")
    BigDecimal getCategoryTotal(
            Long userId,
            String category
    );
    @Query("""
SELECT FUNCTION('DATE_FORMAT', e.createdAt, '%Y-%m'),
       SUM(e.amount)
FROM Expense e
WHERE e.paidBy.id = :userId
GROUP BY FUNCTION('DATE_FORMAT', e.createdAt, '%Y-%m')
ORDER BY FUNCTION('DATE_FORMAT', e.createdAt, '%Y-%m')
""")
    List<Object[]> getMonthlySpending(Long userId);
    @Query("""
SELECT c.name,
       SUM(e.amount)
FROM Expense e
JOIN e.category c
WHERE e.paidBy.id = :userId
GROUP BY c.id, c.name
ORDER BY SUM(e.amount) DESC
""")
    List<Object[]> getCategorySpending(Long userId);


}
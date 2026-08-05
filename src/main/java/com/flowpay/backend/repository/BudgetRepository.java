package com.flowpay.backend.repository;

import com.flowpay.backend.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByUserId(Long userId);

    Optional<Budget> findByUserIdAndCategoryIdAndMonth(
            Long userId,
            Long categoryId,
            String month
    );
}
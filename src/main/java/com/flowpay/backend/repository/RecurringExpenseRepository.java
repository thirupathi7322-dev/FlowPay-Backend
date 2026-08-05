package com.flowpay.backend.repository;

import com.flowpay.backend.entity.RecurringExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecurringExpenseRepository
        extends JpaRepository<RecurringExpense, Long> {

    List<RecurringExpense> findByActiveTrue();

    List<RecurringExpense> findByActiveTrueAndNextRun(LocalDate nextRun);
}
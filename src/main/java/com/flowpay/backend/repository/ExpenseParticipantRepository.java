package com.flowpay.backend.repository;

import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.entity.ExpenseParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseParticipantRepository
        extends JpaRepository<ExpenseParticipant, Long> {

    List<ExpenseParticipant> findByExpense(Expense expense);

}
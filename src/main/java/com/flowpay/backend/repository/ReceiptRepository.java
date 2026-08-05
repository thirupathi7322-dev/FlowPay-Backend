package com.flowpay.backend.repository;

import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceiptRepository
        extends JpaRepository<Receipt, Long> {

    Optional<Receipt> findByExpense(Expense expense);
}
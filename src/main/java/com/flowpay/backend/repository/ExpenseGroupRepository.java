package com.flowpay.backend.repository;

import com.flowpay.backend.entity.ExpenseGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseGroupRepository
        extends JpaRepository<ExpenseGroup, Long> {
    List<ExpenseGroup> findByNameContainingIgnoreCase(String name);

}
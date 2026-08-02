package com.flowpay.backend.repository;

import com.flowpay.backend.entity.ExpenseGroup;
import com.flowpay.backend.entity.GroupMember;
import com.flowpay.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    List<GroupMember> findByExpenseGroup(ExpenseGroup expenseGroup);

    boolean existsByExpenseGroupAndUser(
            ExpenseGroup expenseGroup,
            User user
    );

    @Transactional
    @Modifying
    void deleteByExpenseGroupAndUser(
            ExpenseGroup expenseGroup,
            User user
    );
}
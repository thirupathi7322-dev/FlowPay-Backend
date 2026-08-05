package com.flowpay.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "recurring_expenses")
public class RecurringExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String frequency;

    @Column(nullable = false)
    private LocalDate nextRun;

    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private ExpenseGroup expenseGroup;

    @ManyToOne
    @JoinColumn(name = "paid_by", nullable = false)
    private User paidBy;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public RecurringExpense() {
        this.active = true;
    }

    public RecurringExpense(
            Long id,
            String title,
            BigDecimal amount,
            String frequency,
            LocalDate nextRun,
            boolean active,
            ExpenseGroup expenseGroup,
            User paidBy,
            Category category) {

        this.id = id;
        this.title = title;
        this.amount = amount;
        this.frequency = frequency;
        this.nextRun = nextRun;
        this.active = active;
        this.expenseGroup = expenseGroup;
        this.paidBy = paidBy;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalDate getNextRun() {
        return nextRun;
    }

    public void setNextRun(LocalDate nextRun) {
        this.nextRun = nextRun;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ExpenseGroup getExpenseGroup() {
        return expenseGroup;
    }

    public void setExpenseGroup(ExpenseGroup expenseGroup) {
        this.expenseGroup = expenseGroup;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
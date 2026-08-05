package com.flowpay.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RecurringExpenseRequest {

    @NotBlank
    private String title;

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String frequency;

    @NotNull
    private LocalDate nextRun;

    @NotNull
    private Long groupId;

    @NotNull
    private Long paidById;


    @NotNull
    private Long categoryId;

    public RecurringExpenseRequest() {
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getPaidById() {
        return paidById;
    }

    public void setPaidById(Long paidById) {
        this.paidById = paidById;
    }
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
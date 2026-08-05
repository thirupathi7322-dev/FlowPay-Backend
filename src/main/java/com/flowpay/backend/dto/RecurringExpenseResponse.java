package com.flowpay.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RecurringExpenseResponse {

    private Long id;
    private String title;
    private BigDecimal amount;
    private String frequency;
    private LocalDate nextRun;
    private boolean active;

    public RecurringExpenseResponse() {
    }

    public RecurringExpenseResponse(
            Long id,
            String title,
            BigDecimal amount,
            String frequency,
            LocalDate nextRun,
            boolean active) {

        this.id = id;
        this.title = title;
        this.amount = amount;
        this.frequency = frequency;
        this.nextRun = nextRun;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getFrequency() {
        return frequency;
    }

    public LocalDate getNextRun() {
        return nextRun;
    }

    public boolean isActive() {
        return active;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setNextRun(LocalDate nextRun) {
        this.nextRun = nextRun;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
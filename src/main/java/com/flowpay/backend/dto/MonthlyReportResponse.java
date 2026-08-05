package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class MonthlyReportResponse {

    private String month;
    private BigDecimal totalExpenses;
    private Long totalTransactions;
    private BigDecimal averageExpense;

    public MonthlyReportResponse() {
    }

    public MonthlyReportResponse(
            String month,
            BigDecimal totalExpenses,
            Long totalTransactions,
            BigDecimal averageExpense) {

        this.month = month;
        this.totalExpenses = totalExpenses;
        this.totalTransactions = totalTransactions;
        this.averageExpense = averageExpense;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public Long getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(Long totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public BigDecimal getAverageExpense() {
        return averageExpense;
    }

    public void setAverageExpense(BigDecimal averageExpense) {
        this.averageExpense = averageExpense;
    }
}
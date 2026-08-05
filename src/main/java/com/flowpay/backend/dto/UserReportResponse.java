package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class UserReportResponse {

    private String userName;
    private BigDecimal totalPaid;
    private int totalExpenses;
    private int totalParticipated;
    private BigDecimal averageExpense;

    public UserReportResponse() {
    }

    public UserReportResponse(
            String userName,
            BigDecimal totalPaid,
            int totalExpenses,
            int totalParticipated,
            BigDecimal averageExpense) {

        this.userName = userName;
        this.totalPaid = totalPaid;
        this.totalExpenses = totalExpenses;
        this.totalParticipated = totalParticipated;
        this.averageExpense = averageExpense;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public int getTotalExpenses() {
        return totalExpenses;
    }

    public int getTotalParticipated() {
        return totalParticipated;
    }

    public BigDecimal getAverageExpense() {
        return averageExpense;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }

    public void setTotalExpenses(int totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public void setTotalParticipated(int totalParticipated) {
        this.totalParticipated = totalParticipated;
    }

    public void setAverageExpense(BigDecimal averageExpense) {
        this.averageExpense = averageExpense;
    }
}
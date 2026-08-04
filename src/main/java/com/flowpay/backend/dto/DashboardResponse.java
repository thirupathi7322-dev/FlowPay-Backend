package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class DashboardResponse {

    private String groupName;
    private BigDecimal totalExpenses;
    private int totalMembers;
    private int totalTransactions;

    public DashboardResponse() {
    }

    public DashboardResponse(
            String groupName,
            BigDecimal totalExpenses,
            int totalMembers,
            int totalTransactions) {

        this.groupName = groupName;
        this.totalExpenses = totalExpenses;
        this.totalMembers = totalMembers;
        this.totalTransactions = totalTransactions;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public int getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(int totalMembers) {
        this.totalMembers = totalMembers;
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(int totalTransactions) {
        this.totalTransactions = totalTransactions;
    }
}
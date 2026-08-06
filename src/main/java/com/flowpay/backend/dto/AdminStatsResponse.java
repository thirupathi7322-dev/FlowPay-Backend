package com.flowpay.backend.dto;

public class AdminStatsResponse {

    private long users;
    private long groups;
    private long expenses;
    private long receipts;
    private long budgets;
    private long recurringExpenses;

    public AdminStatsResponse() {
    }

    public AdminStatsResponse(
            long users,
            long groups,
            long expenses,
            long receipts,
            long budgets,
            long recurringExpenses) {

        this.users = users;
        this.groups = groups;
        this.expenses = expenses;
        this.receipts = receipts;
        this.budgets = budgets;
        this.recurringExpenses = recurringExpenses;
    }

    public long getUsers() {
        return users;
    }

    public void setUsers(long users) {
        this.users = users;
    }

    public long getGroups() {
        return groups;
    }

    public void setGroups(long groups) {
        this.groups = groups;
    }

    public long getExpenses() {
        return expenses;
    }

    public void setExpenses(long expenses) {
        this.expenses = expenses;
    }

    public long getReceipts() {
        return receipts;
    }

    public void setReceipts(long receipts) {
        this.receipts = receipts;
    }

    public long getBudgets() {
        return budgets;
    }

    public void setBudgets(long budgets) {
        this.budgets = budgets;
    }

    public long getRecurringExpenses() {
        return recurringExpenses;
    }

    public void setRecurringExpenses(long recurringExpenses) {
        this.recurringExpenses = recurringExpenses;
    }
}
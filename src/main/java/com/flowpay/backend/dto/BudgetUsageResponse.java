package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class BudgetUsageResponse {

    private String month;
    private String category;
    private BigDecimal budget;
    private BigDecimal spent;
    private BigDecimal remaining;
    private double usagePercentage;

    public BudgetUsageResponse() {
    }

    public BudgetUsageResponse(
            String month,
            String category,
            BigDecimal budget,
            BigDecimal spent,
            BigDecimal remaining,
            double usagePercentage) {

        this.month = month;
        this.category = category;
        this.budget = budget;
        this.spent = spent;
        this.remaining = remaining;
        this.usagePercentage = usagePercentage;
    }

    public String getMonth() {
        return month;
    }

    public String getCategory() {
        return category;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public BigDecimal getSpent() {
        return spent;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public double getUsagePercentage() {
        return usagePercentage;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public void setSpent(BigDecimal spent) {
        this.spent = spent;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public void setUsagePercentage(double usagePercentage) {
        this.usagePercentage = usagePercentage;
    }
}
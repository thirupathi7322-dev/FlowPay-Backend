package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class BudgetDashboardResponse {

    private String category;
    private BigDecimal budget;
    private BigDecimal spent;
    private BigDecimal remaining;
    private double usagePercentage;

    public BudgetDashboardResponse() {
    }

    public BudgetDashboardResponse(
            String category,
            BigDecimal budget,
            BigDecimal spent,
            BigDecimal remaining,
            double usagePercentage) {

        this.category = category;
        this.budget = budget;
        this.spent = spent;
        this.remaining = remaining;
        this.usagePercentage = usagePercentage;
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
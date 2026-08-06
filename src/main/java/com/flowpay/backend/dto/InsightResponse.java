package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class InsightResponse {

    private BigDecimal totalSpent;
    private String highestCategory;
    private BigDecimal highestCategoryAmount;
    private String largestExpense;
    private BigDecimal largestExpenseAmount;
    private String insight;

    public InsightResponse() {
    }

    public InsightResponse(
            BigDecimal totalSpent,
            String highestCategory,
            BigDecimal highestCategoryAmount,
            String largestExpense,
            BigDecimal largestExpenseAmount,
            String insight) {

        this.totalSpent = totalSpent;
        this.highestCategory = highestCategory;
        this.highestCategoryAmount = highestCategoryAmount;
        this.largestExpense = largestExpense;
        this.largestExpenseAmount = largestExpenseAmount;
        this.insight = insight;
    }

    public BigDecimal getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(BigDecimal totalSpent) {
        this.totalSpent = totalSpent;
    }

    public String getHighestCategory() {
        return highestCategory;
    }

    public void setHighestCategory(String highestCategory) {
        this.highestCategory = highestCategory;
    }

    public BigDecimal getHighestCategoryAmount() {
        return highestCategoryAmount;
    }

    public void setHighestCategoryAmount(BigDecimal highestCategoryAmount) {
        this.highestCategoryAmount = highestCategoryAmount;
    }

    public String getLargestExpense() {
        return largestExpense;
    }

    public void setLargestExpense(String largestExpense) {
        this.largestExpense = largestExpense;
    }

    public BigDecimal getLargestExpenseAmount() {
        return largestExpenseAmount;
    }

    public void setLargestExpenseAmount(BigDecimal largestExpenseAmount) {
        this.largestExpenseAmount = largestExpenseAmount;
    }

    public String getInsight() {
        return insight;
    }

    public void setInsight(String insight) {
        this.insight = insight;
    }
}
package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class BudgetResponse {

    private Long id;
    private String month;
    private BigDecimal budgetAmount;
    private String categoryName;
    private String userName;

    public BudgetResponse() {
    }

    public BudgetResponse(Long id,
                          String month,
                          BigDecimal budgetAmount,
                          String categoryName,
                          String userName) {

        this.id = id;
        this.month = month;
        this.budgetAmount = budgetAmount;
        this.categoryName = categoryName;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getMonth() {
        return month;
    }

    public BigDecimal getBudgetAmount() {
        return budgetAmount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setBudgetAmount(BigDecimal budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
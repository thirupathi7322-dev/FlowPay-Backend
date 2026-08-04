package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class CategoryAnalyticsResponse {

    private String category;
    private BigDecimal totalAmount;

    public CategoryAnalyticsResponse() {
    }

    public CategoryAnalyticsResponse(
            String category,
            BigDecimal totalAmount) {

        this.category = category;
        this.totalAmount = totalAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
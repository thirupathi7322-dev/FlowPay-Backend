package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class UserAnalyticsResponse {

    private String userName;
    private BigDecimal totalAmount;

    public UserAnalyticsResponse() {
    }

    public UserAnalyticsResponse(
            String userName,
            BigDecimal totalAmount) {

        this.userName = userName;
        this.totalAmount = totalAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
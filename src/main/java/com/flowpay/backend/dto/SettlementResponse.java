package com.flowpay.backend.dto;

import java.math.BigDecimal;

public class SettlementResponse {

    private String fromUser;
    private String toUser;
    private BigDecimal amount;

    public SettlementResponse() {
    }

    public SettlementResponse(
            String fromUser,
            String toUser,
            BigDecimal amount) {

        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
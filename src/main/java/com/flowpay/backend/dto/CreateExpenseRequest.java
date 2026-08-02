package com.flowpay.backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import java.math.BigDecimal;

public class CreateExpenseRequest {

    @NotBlank
    private String title;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal amount;

    @NotNull
    private Long paidByUserId;

    @NotNull
    private List<Long> participantIds;

    public CreateExpenseRequest() {
    }

    public CreateExpenseRequest(
            String title,
            BigDecimal amount,
            Long paidByUserId,
            List<Long> participantIds) {

        this.title = title;
        this.amount = amount;
        this.paidByUserId = paidByUserId;
        this.participantIds = participantIds;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getPaidByUserId() {
        return paidByUserId;
    }

    public void setPaidByUserId(Long paidByUserId) {
        this.paidByUserId = paidByUserId;
    }

    public List<Long> getParticipantIds() {
        return participantIds;
    }

    public void setParticipantIds(List<Long> participantIds) {
        this.participantIds = participantIds;
    }
}
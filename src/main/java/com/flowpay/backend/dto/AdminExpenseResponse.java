package com.flowpay.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AdminExpenseResponse {

    private Long id;
    private String title;
    private BigDecimal amount;
    private String category;
    private String paidBy;
    private String groupName;
    private LocalDateTime createdAt;

    public AdminExpenseResponse() {
    }

    public AdminExpenseResponse(
            Long id,
            String title,
            BigDecimal amount,
            String category,
            String paidBy,
            String groupName,
            LocalDateTime createdAt) {

        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.paidBy = paidBy;
        this.groupName = groupName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public String getGroupName() {
        return groupName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
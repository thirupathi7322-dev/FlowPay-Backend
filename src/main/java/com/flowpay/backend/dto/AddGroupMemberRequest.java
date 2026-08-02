package com.flowpay.backend.dto;

import jakarta.validation.constraints.NotNull;

public class AddGroupMemberRequest {

    @NotNull(message = "User ID is required")
    private Long userId;

    public AddGroupMemberRequest() {
    }

    public AddGroupMemberRequest(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
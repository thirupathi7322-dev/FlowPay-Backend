package com.flowpay.backend.dto;

public class AdminGroupResponse {

    private Long id;
    private String name;
    private String description;
    private long memberCount;

    public AdminGroupResponse() {
    }

    public AdminGroupResponse(
            Long id,
            String name,
            String description,
            long memberCount) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.memberCount = memberCount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMemberCount(long memberCount) {
        this.memberCount = memberCount;
    }
}
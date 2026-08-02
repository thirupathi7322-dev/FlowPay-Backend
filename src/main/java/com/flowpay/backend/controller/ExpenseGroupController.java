package com.flowpay.backend.controller;

import com.flowpay.backend.dto.CreateExpenseGroupRequest;
import com.flowpay.backend.dto.ExpenseGroupResponse;
import com.flowpay.backend.service.ExpenseGroupService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.flowpay.backend.dto.AddGroupMemberRequest;
import com.flowpay.backend.dto.GroupMemberResponse;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class ExpenseGroupController {

    private final ExpenseGroupService expenseGroupService;

    public ExpenseGroupController(ExpenseGroupService expenseGroupService) {
        this.expenseGroupService = expenseGroupService;
    }

    @PostMapping
    public ExpenseGroupResponse createGroup(
            @Valid @RequestBody CreateExpenseGroupRequest request) {

        return expenseGroupService.createGroup(request);
    }

    @GetMapping
    public List<ExpenseGroupResponse> getAllGroups() {

        return expenseGroupService.getAllGroups();
    }
    @PostMapping("/{groupId}/members")
    public String addMember(
            @PathVariable Long groupId,
            @Valid @RequestBody AddGroupMemberRequest request) {

        return expenseGroupService.addMember(groupId, request);
    }
    @GetMapping("/{groupId}/members")
    public List<GroupMemberResponse> getGroupMembers(
            @PathVariable Long groupId) {

        return expenseGroupService.getGroupMembers(groupId);
    }
    @DeleteMapping("/{groupId}/members/{userId}")
    public String removeMember(
            @PathVariable Long groupId,
            @PathVariable Long userId) {
        System.out.println("DELETE API HIT");

        return expenseGroupService.removeMember(groupId, userId);
    }
}
package com.flowpay.backend.controller;

import com.flowpay.backend.dto.AddGroupMemberRequest;
import com.flowpay.backend.dto.CreateExpenseGroupRequest;
import com.flowpay.backend.dto.ExpenseGroupResponse;
import com.flowpay.backend.dto.GroupMemberResponse;
import com.flowpay.backend.service.ExpenseGroupService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class ExpenseGroupController {

    private static final Logger logger =
            LoggerFactory.getLogger(ExpenseGroupController.class);

    private final ExpenseGroupService expenseGroupService;

    public ExpenseGroupController(ExpenseGroupService expenseGroupService) {
        this.expenseGroupService = expenseGroupService;
    }

    @PostMapping
    public ExpenseGroupResponse createGroup(
            @Valid @RequestBody CreateExpenseGroupRequest request) {

        logger.info("Creating expense group: {}", request.getName());

        return expenseGroupService.createGroup(request);
    }

    @GetMapping
    public List<ExpenseGroupResponse> getAllGroups() {

        logger.info("Fetching all expense groups.");

        return expenseGroupService.getAllGroups();
    }

    @PostMapping("/{groupId}/members")
    public String addMember(
            @PathVariable Long groupId,
            @Valid @RequestBody AddGroupMemberRequest request) {

        logger.info(
                "Adding userId={} to groupId={}",
                request.getUserId(),
                groupId
        );

        return expenseGroupService.addMember(groupId, request);
    }

    @GetMapping("/{groupId}/members")
    public List<GroupMemberResponse> getGroupMembers(
            @PathVariable Long groupId) {

        logger.info("Fetching members for groupId={}", groupId);

        return expenseGroupService.getGroupMembers(groupId);
    }

    @DeleteMapping("/{groupId}/members/{userId}")
    public String removeMember(
            @PathVariable Long groupId,
            @PathVariable Long userId) {

        logger.info(
                "Removing userId={} from groupId={}",
                userId,
                groupId
        );

        return expenseGroupService.removeMember(groupId, userId);
    }
}
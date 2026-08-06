package com.flowpay.backend.controller;

import com.flowpay.backend.dto.AdminStatsResponse;
import com.flowpay.backend.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.flowpay.backend.dto.AdminUserResponse;
import com.flowpay.backend.dto.AdminGroupResponse;
import com.flowpay.backend.dto.AdminGroupResponse;
import java.util.List;
import com.flowpay.backend.dto.AdminExpenseResponse;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/stats")
    public AdminStatsResponse getStats() {
        return adminService.getStats();
    }
    @GetMapping("/users")
    public List<AdminUserResponse> getAllUsers() {

        return adminService.getAllUsers();
    }
    @GetMapping("/groups")
    public List<AdminGroupResponse> getAllGroups() {

        return adminService.getAllGroups();
    }
    @GetMapping("/expenses")
    public List<AdminExpenseResponse> getAllExpenses() {

        return adminService.getAllExpenses();
    }
    @GetMapping("/users/search")
    public List<AdminUserResponse> searchUsers(
            @RequestParam String name) {

        return adminService.searchUsers(name);
    }
    @GetMapping("/groups/search")
    public List<AdminGroupResponse> searchGroups(
            @RequestParam String name) {

        return adminService.searchGroups(name);
    }

}
package com.flowpay.backend.controller;

import com.flowpay.backend.dto.DashboardResponse;
import com.flowpay.backend.service.DashboardService;
import org.springframework.web.bind.annotation.*;
import com.flowpay.backend.dto.CategoryAnalyticsResponse;
import com.flowpay.backend.dto.UserAnalyticsResponse;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/{groupId}/dashboard")
    public DashboardResponse getDashboard(
            @PathVariable Long groupId) {

        return dashboardService.getDashboard(groupId);
    }
    @GetMapping("/{groupId}/analytics/category")
    public List<CategoryAnalyticsResponse> getCategoryAnalytics(
            @PathVariable Long groupId) {

        return dashboardService.getCategoryAnalytics(groupId);
    }
    @GetMapping("/{groupId}/analytics/user")
    public List<UserAnalyticsResponse> getUserAnalytics(
            @PathVariable Long groupId) {

        return dashboardService.getUserAnalytics(groupId);
    }

}
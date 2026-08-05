package com.flowpay.backend.controller;

import com.flowpay.backend.dto.MonthlyReportResponse;
import com.flowpay.backend.service.ReportService;
import org.springframework.web.bind.annotation.*;
import com.flowpay.backend.dto.UserReportResponse;

@RestController
@RequestMapping("/api/groups/{groupId}/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @GetMapping("/monthly")
    public MonthlyReportResponse getMonthlyReport(
            @PathVariable Long groupId) {

        System.out.println("===== REPORT CONTROLLER HIT =====");

        return reportService.getMonthlyReport(groupId);
    }
    @GetMapping("/user/{userId}")
    public UserReportResponse getUserReport(
            @PathVariable Long groupId,
            @PathVariable Long userId) {

        return reportService.getUserReport(groupId, userId);
    }
}
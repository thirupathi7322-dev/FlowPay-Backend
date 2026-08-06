package com.flowpay.backend.controller;

import com.flowpay.backend.dto.MonthlyReportResponse;
import com.flowpay.backend.dto.UserReportResponse;
import com.flowpay.backend.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups/{groupId}/reports")
public class ReportController {

    private static final Logger logger =
            LoggerFactory.getLogger(ReportController.class);

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/monthly")
    public MonthlyReportResponse getMonthlyReport(
            @PathVariable Long groupId) {

        logger.info("Generating monthly report for groupId={}", groupId);

        return reportService.getMonthlyReport(groupId);
    }

    @GetMapping("/user/{userId}")
    public UserReportResponse getUserReport(
            @PathVariable Long groupId,
            @PathVariable Long userId) {

        logger.info(
                "Generating user report for groupId={} and userId={}",
                groupId,
                userId
        );

        return reportService.getUserReport(groupId, userId);
    }
}
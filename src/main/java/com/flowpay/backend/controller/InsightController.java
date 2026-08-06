package com.flowpay.backend.controller;

import com.flowpay.backend.dto.InsightResponse;
import com.flowpay.backend.service.InsightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.flowpay.backend.dto.MonthlyTrendResponse;
import java.util.List;
import com.flowpay.backend.dto.CategorySpendingResponse;
import com.flowpay.backend.dto.RecommendationResponse;

@RestController
@RequestMapping("/api/insights")
public class InsightController {

    private final InsightService insightService;

    public InsightController(InsightService insightService) {
        this.insightService = insightService;
    }

    @GetMapping
    public InsightResponse getInsights(
            @RequestParam Long userId) {

        return insightService.getInsights(userId);
    }
    @GetMapping("/monthly-trend")
    public List<MonthlyTrendResponse> getMonthlyTrend(
            @RequestParam Long userId) {

        return insightService.getMonthlyTrend(userId);
    }
    @GetMapping("/category-breakdown")
    public List<CategorySpendingResponse> getCategoryBreakdown(
            @RequestParam Long userId) {

        return insightService.getCategorySpending(userId);
    }
    @GetMapping("/recommendations")
    public RecommendationResponse getRecommendations(
            @RequestParam Long userId) {

        return insightService.getRecommendations(userId);
    }
}
package com.flowpay.backend.service;

import com.flowpay.backend.dto.InsightResponse;
import com.flowpay.backend.entity.Expense;
import com.flowpay.backend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;
import com.flowpay.backend.dto.MonthlyTrendResponse;
import java.util.ArrayList;
import com.flowpay.backend.dto.CategorySpendingResponse;
import com.flowpay.backend.dto.RecommendationResponse;
import java.util.ArrayList;

import java.math.BigDecimal;
import java.util.List;

@Service
public class InsightService {

    private final ExpenseRepository expenseRepository;

    public InsightService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public InsightResponse getInsights(Long userId) {

        BigDecimal totalSpent =
                expenseRepository.getTotalSpentByUser(userId);

        if (totalSpent == null) {
            totalSpent = BigDecimal.ZERO;
        }

        String highestCategory = "N/A";
        BigDecimal highestCategoryAmount = BigDecimal.ZERO;

        List<Object[]> categoryTotals =
                expenseRepository.getCategoryTotals(userId);

        if (!categoryTotals.isEmpty()) {

            Object[] row = categoryTotals.get(0);

            highestCategory = (String) row[0];
            highestCategoryAmount = (BigDecimal) row[1];
        }

        Expense largestExpense =
                expenseRepository.findFirstByPaidByIdOrderByAmountDesc(userId);

        String largestExpenseTitle = "N/A";
        BigDecimal largestExpenseAmount = BigDecimal.ZERO;

        if (largestExpense != null) {

            largestExpenseTitle = largestExpense.getTitle();
            largestExpenseAmount = largestExpense.getAmount();
        }

        double percentage = 0;

        if (totalSpent.compareTo(BigDecimal.ZERO) > 0) {

            percentage =
                    highestCategoryAmount.doubleValue() * 100
                            / totalSpent.doubleValue();
        }

        String insight =
                "You spent "
                        + Math.round(percentage)
                        + "% of your money on "
                        + highestCategory + ".";

        return new InsightResponse(
                totalSpent,
                highestCategory,
                highestCategoryAmount,
                largestExpenseTitle,
                largestExpenseAmount,
                insight
        );
    }
    public List<MonthlyTrendResponse> getMonthlyTrend(Long userId) {

        List<Object[]> result =
                expenseRepository.getMonthlySpending(userId);

        List<MonthlyTrendResponse> response =
                new ArrayList<>();

        for (Object[] row : result) {

            response.add(
                    new MonthlyTrendResponse(
                            (String) row[0],
                            (BigDecimal) row[1]
                    )
            );
        }

        return response;
    }
    public List<CategorySpendingResponse> getCategorySpending(
            Long userId) {

        List<Object[]> rows =
                expenseRepository.getCategorySpending(userId);

        List<CategorySpendingResponse> response =
                new ArrayList<>();

        for (Object[] row : rows) {

            response.add(
                    new CategorySpendingResponse(
                            (String) row[0],
                            (BigDecimal) row[1]
                    )
            );
        }

        return response;
    }
    public RecommendationResponse getRecommendations(Long userId) {

        InsightResponse insight = getInsights(userId);

        List<String> recommendations = new ArrayList<>();

        recommendations.add(
                "Your total spending is ₹"
                        + insight.getTotalSpent() + "."
        );

        recommendations.add(
                "Your highest spending category is "
                        + insight.getHighestCategory()
                        + " (₹"
                        + insight.getHighestCategoryAmount()
                        + ")."
        );

        recommendations.add(
                "Your largest expense is "
                        + insight.getLargestExpense()
                        + " (₹"
                        + insight.getLargestExpenseAmount()
                        + ")."
        );

        if ("Food".equalsIgnoreCase(insight.getHighestCategory())) {

            recommendations.add(
                    "Food accounts for most of your spending. Consider cooking at home more often."
            );

        } else if ("Entertainment".equalsIgnoreCase(insight.getHighestCategory())) {

            recommendations.add(
                    "Entertainment is your biggest expense. Review your subscriptions and outings."
            );

        } else if ("Bills".equalsIgnoreCase(insight.getHighestCategory())) {

            recommendations.add(
                    "Bills are your largest expense. Check if you can reduce utility or subscription costs."
            );

        } else {

            recommendations.add(
                    "Keep monitoring your spending habits to stay within budget."
            );
        }

        return new RecommendationResponse(recommendations);
    }
}
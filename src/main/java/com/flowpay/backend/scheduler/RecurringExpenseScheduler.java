package com.flowpay.backend.scheduler;

import com.flowpay.backend.service.RecurringExpenseService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecurringExpenseScheduler {

    private final RecurringExpenseService recurringExpenseService;

    public RecurringExpenseScheduler(
            RecurringExpenseService recurringExpenseService) {

        this.recurringExpenseService = recurringExpenseService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void processRecurringExpenses() {

        System.out.println("===== Scheduler Running =====");

        recurringExpenseService.processRecurringExpenses();
    }
}
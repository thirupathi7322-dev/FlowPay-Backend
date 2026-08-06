package com.flowpay.backend.scheduler;

import com.flowpay.backend.service.RecurringExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RecurringExpenseScheduler {

    private static final Logger logger =
            LoggerFactory.getLogger(RecurringExpenseScheduler.class);

    private final RecurringExpenseService recurringExpenseService;

    public RecurringExpenseScheduler(
            RecurringExpenseService recurringExpenseService) {

        this.recurringExpenseService = recurringExpenseService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void processRecurringExpenses() {

        logger.info("Recurring expense scheduler started.");

        recurringExpenseService.processRecurringExpenses();
    }
}
package com.flowpay.backend.service;

import com.flowpay.backend.entity.User;
import com.flowpay.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReminderScheduler {

    private static final Logger logger =
            LoggerFactory.getLogger(ReminderScheduler.class);

    private final UserRepository userRepository;
    private final NotificationService notificationService;

    public ReminderScheduler(
            UserRepository userRepository,
            NotificationService notificationService) {

        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "0 0 9 * * *")
    public void sendDailyReminder() {

        logger.info("Starting daily reminder scheduler.");

        for (User user : userRepository.findAll()) {

            notificationService.createNotification(
                    user.getId(),
                    "🔔 Daily Reminder: Check your pending expenses and settlements."
            );
        }

        logger.info("Daily reminders completed successfully.");
    }
}
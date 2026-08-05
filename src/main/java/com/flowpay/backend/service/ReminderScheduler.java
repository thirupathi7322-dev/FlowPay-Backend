package com.flowpay.backend.service;

import com.flowpay.backend.entity.User;
import com.flowpay.backend.repository.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ReminderScheduler {

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

        System.out.println("Running Daily Reminder...");

        for (User user : userRepository.findAll()) {

            notificationService.createNotification(
                    user.getId(),
                    "🔔 Daily Reminder: Check your pending expenses and settlements."
            );
        }

        System.out.println("Daily reminders completed.");
    }
}
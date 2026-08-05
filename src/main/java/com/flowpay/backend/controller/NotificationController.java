package com.flowpay.backend.controller;

import com.flowpay.backend.dto.CreateNotificationRequest;
import com.flowpay.backend.dto.NotificationResponse;
import com.flowpay.backend.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @PostMapping("/notifications")
    public NotificationResponse createNotification(
            @Valid @RequestBody CreateNotificationRequest request) {

        return notificationService.createNotification(request);
    }

    @GetMapping("/users/{userId}/notifications")
    public List<NotificationResponse> getUserNotifications(
            @PathVariable Long userId) {

        return notificationService.getUserNotifications(userId);
    }

    @PutMapping("/notifications/{notificationId}/read")
    public NotificationResponse markAsRead(
            @PathVariable Long notificationId) {

        return notificationService.markAsRead(notificationId);
    }
}
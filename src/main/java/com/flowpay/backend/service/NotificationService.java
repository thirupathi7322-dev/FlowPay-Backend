package com.flowpay.backend.service;

import com.flowpay.backend.dto.CreateNotificationRequest;
import com.flowpay.backend.dto.NotificationResponse;
import com.flowpay.backend.entity.Notification;
import com.flowpay.backend.entity.User;
import com.flowpay.backend.repository.NotificationRepository;
import com.flowpay.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(
            NotificationRepository notificationRepository,
            UserRepository userRepository) {

        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public NotificationResponse createNotification(
            CreateNotificationRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Notification notification = new Notification();

        notification.setUser(user);
        notification.setMessage(request.getMessage());

        Notification saved =
                notificationRepository.save(notification);

        return new NotificationResponse(
                saved.getId(),
                saved.getMessage(),
                saved.isRead(),
                saved.getCreatedAt()
        );
    }

    public List<NotificationResponse> getUserNotifications(
            Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return notificationRepository
                .findByUserOrderByCreatedAtDesc(user)
                .stream()
                .map(notification -> new NotificationResponse(
                        notification.getId(),
                        notification.getMessage(),
                        notification.isRead(),
                        notification.getCreatedAt()
                ))
                .toList();
    }

    public NotificationResponse markAsRead(Long notificationId) {

        Notification notification =
                notificationRepository.findById(notificationId)
                        .orElseThrow(() ->
                                new RuntimeException("Notification not found"));

        notification.setRead(true);

        Notification updated =
                notificationRepository.save(notification);

        return new NotificationResponse(
                updated.getId(),
                updated.getMessage(),
                updated.isRead(),
                updated.getCreatedAt()
        );
    }
    public void createNotification(Long userId, String message) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Notification notification = new Notification();

        notification.setUser(user);
        notification.setMessage(message);

        notificationRepository.save(notification);
    }
}
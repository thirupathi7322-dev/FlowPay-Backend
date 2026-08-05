package com.flowpay.backend.repository;

import com.flowpay.backend.entity.Notification;
import com.flowpay.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository
        extends JpaRepository<Notification, Long> {

    List<Notification> findByUser(User user);

    List<Notification> findByUserOrderByCreatedAtDesc(User user);
}
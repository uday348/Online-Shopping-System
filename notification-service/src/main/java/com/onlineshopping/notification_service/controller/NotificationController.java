package com.onlineshopping.notification_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.onlineshopping.notification_service.dto.NotificationRequest;
import com.onlineshopping.notification_service.dto.NotificationResponse;
import com.onlineshopping.notification_service.service.NotificationService;

@RestController
@RequestMapping("/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // ================= INTERNAL API =================

    // Called by Order Service using OpenFeign
    @PostMapping
    public NotificationResponse sendNotification(
            @RequestBody NotificationRequest notificationRequest) {

        return notificationService.sendNotification(notificationRequest);
    }

    // ================= USER API =================

    // View Logged-in User Notifications
    @GetMapping("/user/{userId}")
    public List<NotificationResponse> getNotificationsByUserId(
            @PathVariable Long userId) {

        return notificationService.getNotificationsByUserId(userId);
    }

    // ================= ADMIN APIs =================

    // View All Notifications
    @GetMapping
    public List<NotificationResponse> getAllNotifications() {

        return notificationService.getAllNotifications();
    }

    // View Notification By Id
    @GetMapping("/{notificationId}")
    public NotificationResponse getNotificationById(
            @PathVariable Long notificationId) {

        return notificationService.getNotificationById(notificationId);
    }

    // Delete Notification
    @DeleteMapping("/{notificationId}")
    public String deleteNotification(
            @PathVariable Long notificationId) {

        return notificationService.deleteNotification(notificationId);
    }
}
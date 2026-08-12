package com.onlineshopping.notification_service.service;

import java.util.List;

import com.onlineshopping.notification_service.dto.NotificationRequest;
import com.onlineshopping.notification_service.dto.NotificationResponse;

public interface NotificationService {

    NotificationResponse sendNotification(NotificationRequest notificationRequest);

    List<NotificationResponse> getAllNotifications();

    NotificationResponse getNotificationById(Long notificationId);

    List<NotificationResponse> getNotificationsByUserId(Long userId);

    String deleteNotification(Long notificationId);

}
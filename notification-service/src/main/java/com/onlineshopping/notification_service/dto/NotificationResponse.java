package com.onlineshopping.notification_service.dto;

import java.time.LocalDateTime;

import com.onlineshopping.notification_service.entity.NotificationStatus;

public class NotificationResponse {

    private Long notificationId;
    private Long userId;
    private String message;
    private LocalDateTime sentDateTime;
    private NotificationStatus notificationStatus;

    // Default Constructor

    public NotificationResponse() {
    }

    // Parameterized Constructor

    public NotificationResponse(Long notificationId, Long userId,
                                String message,
                                LocalDateTime sentDateTime,
                                NotificationStatus notificationStatus) {

        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.sentDateTime = sentDateTime;
        this.notificationStatus = notificationStatus;
    }

    // Getter and Setter

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

}
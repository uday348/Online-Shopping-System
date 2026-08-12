package com.onlineshopping.notification_service.dto;

public class NotificationRequest {

    private Long userId;
    private String message;

    // Default Constructor

    public NotificationRequest() {
    }

    // Parameterized Constructor

    public NotificationRequest(Long userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    // Getter and Setter for userId

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter and Setter for message

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
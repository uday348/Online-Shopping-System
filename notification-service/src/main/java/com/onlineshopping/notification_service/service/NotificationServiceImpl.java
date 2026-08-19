package com.onlineshopping.notification_service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.notification_service.dto.NotificationRequest;
import com.onlineshopping.notification_service.dto.NotificationResponse;
import com.onlineshopping.notification_service.entity.Notification;
import com.onlineshopping.notification_service.entity.NotificationStatus;
import com.onlineshopping.notification_service.exception.ResourceNotFoundException;
import com.onlineshopping.notification_service.repository.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {

        Notification notification = new Notification();

        notification.setUserId(notificationRequest.getUserId());
        notification.setMessage(notificationRequest.getMessage());
        notification.setSentDateTime(LocalDateTime.now());
        notification.setNotificationStatus(NotificationStatus.SENT);

        Notification savedNotification = notificationRepository.save(notification);

        return convertToResponse(savedNotification);
    }

    @Override
    public List<NotificationResponse> getAllNotifications() {

        List<Notification> notifications = notificationRepository.findAll();
        List<NotificationResponse> responseList = new ArrayList<>();

        for (Notification notification : notifications) {
            responseList.add(convertToResponse(notification));
        }

        return responseList;
    }

    @Override
    public NotificationResponse getNotificationById(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notification not found with ID : " + notificationId));

        return convertToResponse(notification);
    }

    @Override
    public List<NotificationResponse> getNotificationsByUserId(Long userId) {

        List<Notification> notifications = notificationRepository.findByUserId(userId);
        List<NotificationResponse> responseList = new ArrayList<>();

        for (Notification notification : notifications) {
            responseList.add(convertToResponse(notification));
        }

        return responseList;
    }

    @Override
    public String deleteNotification(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Notification not found with ID : " + notificationId));

        notificationRepository.delete(notification);

        return "Notification deleted successfully.";
    }

    private NotificationResponse convertToResponse(Notification notification) {

        NotificationResponse response = new NotificationResponse();

        response.setNotificationId(notification.getNotificationId());
        response.setUserId(notification.getUserId());
        response.setMessage(notification.getMessage());
        response.setSentDateTime(notification.getSentDateTime());
        response.setNotificationStatus(notification.getNotificationStatus());

        return response;
    }

}
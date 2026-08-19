package com.onlineshopping.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlineshopping.order_service.dto.NotificationRequest;
import com.onlineshopping.order_service.dto.NotificationResponse;

@FeignClient(
        name = "NOTIFICATION-SERVICE",
        url = "http://localhost:8006"
)
public interface NotificationClient {

    @PostMapping("/notifications")
    NotificationResponse sendNotification(@RequestBody NotificationRequest notificationRequest);

}
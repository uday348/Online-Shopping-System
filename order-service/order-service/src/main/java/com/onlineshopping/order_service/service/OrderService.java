package com.onlineshopping.order_service.service;

import java.util.List;

import com.onlineshopping.order_service.dto.OrderRequest;
import com.onlineshopping.order_service.dto.OrderResponse;
import com.onlineshopping.order_service.entity.OrderStatus;

public interface OrderService {

    // Place Order
    OrderResponse placeOrder(OrderRequest orderRequest);

    // Get All Orders
    List<OrderResponse> getAllOrders();

    // Get Order By Id
    OrderResponse getOrderById(Long orderId);

    // Update Order Status
    OrderResponse updateOrderStatus(Long orderId, OrderStatus orderStatus);

    // Delete Order
    String deleteOrder(Long orderId);

    // Get Orders By User Id
    List<OrderResponse> getOrdersByUserId(Long userId);

}
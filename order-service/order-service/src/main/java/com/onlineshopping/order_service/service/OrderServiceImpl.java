package com.onlineshopping.order_service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.order_service.dto.NotificationRequest;
import com.onlineshopping.order_service.dto.OrderRequest;
import com.onlineshopping.order_service.dto.OrderResponse;
import com.onlineshopping.order_service.dto.ProductResponse;
import com.onlineshopping.order_service.entity.Order;
import com.onlineshopping.order_service.entity.OrderStatus;
import com.onlineshopping.order_service.exception.ResourceNotFoundException;
import com.onlineshopping.order_service.feign.NotificationClient;
import com.onlineshopping.order_service.feign.ProductClient;
import com.onlineshopping.order_service.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private NotificationClient notificationClient;

    @Override
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        // Fetch product details from Product Service
        ProductResponse product =
                productClient.getProductById(orderRequest.getProductId());

        Order order = new Order();

        order.setUserId(orderRequest.getUserId());
        order.setProductId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());

        // Calculate total price
        order.setTotalPrice(product.getPrice() * orderRequest.getQuantity());

        order.setOrderDate(LocalDate.now());
        order.setOrderStatus(OrderStatus.PENDING);

        // Save Order
        Order savedOrder = orderRepository.save(order);

        // Send Notification
        NotificationRequest notification = new NotificationRequest();
        notification.setUserId(savedOrder.getUserId());

        String message =
                "Your order has been placed successfully.\n\n" +

                "Product : " + product.getProductName() + "\n" +

                "Order ID : " + savedOrder.getOrderId() + "\n" +

                "Amount : ₹" + savedOrder.getTotalPrice() + "\n\n" +

                "Thank you for choosing Online Shopping.";

        notification.setMessage(message);

        notificationClient.sendNotification(notification);

        return convertToResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> responseList = new ArrayList<>();

        for (Order order : orders) {
            responseList.add(convertToResponse(order));
        }

        return responseList;
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found with ID : " + orderId));

        return convertToResponse(order);
    }

    @Override
    public OrderResponse updateOrderStatus(Long orderId, OrderStatus orderStatus) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found with ID : " + orderId));

        order.setOrderStatus(orderStatus);

        Order updatedOrder = orderRepository.save(order);

        return convertToResponse(updatedOrder);
    }

    @Override
    public String deleteOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Order not found with ID : " + orderId));

        orderRepository.delete(order);

        return "Order deleted successfully.";
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(Long userId) {

        List<Order> orders = orderRepository.findByUserId(userId);
        List<OrderResponse> responseList = new ArrayList<>();

        for (Order order : orders) {
            responseList.add(convertToResponse(order));
        }

        return responseList;
    }

    private OrderResponse convertToResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setOrderId(order.getOrderId());
        response.setUserId(order.getUserId());
        response.setProductId(order.getProductId());
        response.setQuantity(order.getQuantity());
        response.setTotalPrice(order.getTotalPrice());
        response.setOrderDate(order.getOrderDate());
        response.setOrderStatus(order.getOrderStatus());

        return response;
    }
}
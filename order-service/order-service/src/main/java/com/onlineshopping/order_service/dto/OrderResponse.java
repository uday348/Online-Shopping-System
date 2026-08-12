package com.onlineshopping.order_service.dto;

import java.time.LocalDate;

import com.onlineshopping.order_service.entity.OrderStatus;

public class OrderResponse {

    private Long orderId;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private Double totalPrice;
    private LocalDate orderDate;
    private OrderStatus orderStatus;

    // Default Constructor
    public OrderResponse() {
    }

    // Parameterized Constructor
    public OrderResponse(Long orderId, Long userId, Long productId,
                         Integer quantity, Double totalPrice,
                         LocalDate orderDate, OrderStatus orderStatus) {

        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    // Getter and Setter for orderId

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    // Getter and Setter for userId

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Getter and Setter for productId

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    // Getter and Setter for quantity

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Getter and Setter for totalPrice

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Getter and Setter for orderDate

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    // Getter and Setter for orderStatus

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

}
package com.onlineshopping.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.onlineshopping.order_service.dto.OrderRequest;
import com.onlineshopping.order_service.dto.OrderResponse;
import com.onlineshopping.order_service.entity.OrderStatus;
import com.onlineshopping.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ================= USER APIs =================

    // Place Order
    @PostMapping
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {

        return orderService.placeOrder(orderRequest);
    }

    // Get Orders By User Id
    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrdersByUserId(@PathVariable Long userId) {

        return orderService.getOrdersByUserId(userId);
    }

    // ================= ADMIN APIs =================

    // Get All Orders
    @GetMapping
    public List<OrderResponse> getAllOrders() {

        return orderService.getAllOrders();
    }

    // Get Order By Id
    @GetMapping("/{orderId}")
    public OrderResponse getOrderById(@PathVariable Long orderId) {

        return orderService.getOrderById(orderId);
    }

    // Update Order Status
    @PutMapping("/{orderId}/{orderStatus}")
    public OrderResponse updateOrderStatus(@PathVariable Long orderId,
                                           @PathVariable OrderStatus orderStatus) {

        return orderService.updateOrderStatus(orderId, orderStatus);
    }

    // Delete Order
    @DeleteMapping("/{orderId}")
    public String deleteOrder(@PathVariable Long orderId) {

        return orderService.deleteOrder(orderId);
    }
}
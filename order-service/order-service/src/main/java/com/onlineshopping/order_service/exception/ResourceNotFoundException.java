package com.onlineshopping.order_service.exception;

public class ResourceNotFoundException extends RuntimeException {

    // Constructor
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
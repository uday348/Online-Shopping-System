package com.onlineshopping.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.onlineshopping.order_service.dto.ProductResponse;

@FeignClient(
    name = "PRODUCT-SERVICE",
    url = "http://localhost:8004"
)
public interface ProductClient {

    @GetMapping("/products/{productId}")
    ProductResponse getProductById(@PathVariable("productId") Long productId);

}
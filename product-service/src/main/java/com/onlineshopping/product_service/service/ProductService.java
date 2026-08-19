package com.onlineshopping.product_service.service;

import java.util.List;

import com.onlineshopping.product_service.dto.ProductRequest;
import com.onlineshopping.product_service.dto.ProductResponse;
import com.onlineshopping.product_service.entity.Category;

public interface ProductService {

    // Add a new product
    ProductResponse addProduct(ProductRequest productRequest);

    // Get all products
    List<ProductResponse> getAllProducts();

    // Get product by ID
    ProductResponse getProductById(Long productId);

    // Update product
    ProductResponse updateProduct(Long productId, ProductRequest productRequest);

    // Delete product
    String deleteProduct(Long productId);

    // Get products by category
    List<ProductResponse> getProductsByCategory(Category category);

}
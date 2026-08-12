package com.onlineshopping.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.onlineshopping.product_service.dto.ProductRequest;
import com.onlineshopping.product_service.dto.ProductResponse;
import com.onlineshopping.product_service.entity.Category;
import com.onlineshopping.product_service.service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Add Product
    @PostMapping
    public ProductResponse addProduct(@RequestBody ProductRequest productRequest) {
        return productService.addProduct(productRequest);
    }

    // Get All Products
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get Product By Id
    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    // Update Product
    @PutMapping("/{productId}")
    public ProductResponse updateProduct(@PathVariable Long productId,
                                         @RequestBody ProductRequest productRequest) {

        return productService.updateProduct(productId, productRequest);
    }

    // Delete Product
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }

    // Get Products By Category
    @GetMapping("/category/{category}")
    public List<ProductResponse> getProductsByCategory(@PathVariable Category category) {
        return productService.getProductsByCategory(category);
    }
}
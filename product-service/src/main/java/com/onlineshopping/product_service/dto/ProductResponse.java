package com.onlineshopping.product_service.dto;

import com.onlineshopping.product_service.entity.Category;

public class ProductResponse {

    private Long productId;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    private Category category;
    private String imageUrl;

    // Default Constructor
    public ProductResponse() {
    }

    // Parameterized Constructor
    public ProductResponse(Long productId, String productName,
                           String description, Double price,
                           Integer stock, Category category,
                           String imageUrl) {

        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    // Getter and Setter for productId

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    // Getter and Setter for productName

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter and Setter for description

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter and Setter for price

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // Getter and Setter for stock

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    // Getter and Setter for category

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // Getter and Setter for imageUrl

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
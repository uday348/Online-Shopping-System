package com.onlineshopping.product_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshopping.product_service.dto.ProductRequest;
import com.onlineshopping.product_service.dto.ProductResponse;
import com.onlineshopping.product_service.entity.Category;
import com.onlineshopping.product_service.entity.Product;
import com.onlineshopping.product_service.exception.ResourceNotFoundException;
import com.onlineshopping.product_service.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {

        Product product = new Product();

        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());

        Product savedProduct = productRepository.save(product);

        return convertToResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();
        List<ProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            responseList.add(convertToResponse(product));
        }

        return responseList;
    }

    @Override
    public ProductResponse getProductById(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with ID : " + productId));

        return convertToResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with ID : " + productId));

        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setCategory(productRequest.getCategory());
        product.setImageUrl(productRequest.getImageUrl());

        Product updatedProduct = productRepository.save(product);

        return convertToResponse(updatedProduct);
    }

    @Override
    public String deleteProduct(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found with ID : " + productId));

        productRepository.delete(product);

        return "Product deleted successfully.";
    }

    @Override
    public List<ProductResponse> getProductsByCategory(Category category) {

        List<Product> products = productRepository.findByCategory(category);
        List<ProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            responseList.add(convertToResponse(product));
        }

        return responseList;
    }

    // Convert Entity to DTO
    private ProductResponse convertToResponse(Product product) {

        ProductResponse response = new ProductResponse();

        response.setProductId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setCategory(product.getCategory());
        response.setImageUrl(product.getImageUrl());

        return response;
    }

}
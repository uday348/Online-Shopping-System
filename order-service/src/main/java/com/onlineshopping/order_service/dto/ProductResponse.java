package com.onlineshopping.order_service.dto;

public class ProductResponse {

    private Long productId;
    private String productName;
    private String description;
    private String category;
    private Double price;
    private Integer quantity;
    private String imageUrl;
    
    
    public ProductResponse() {
    	
    }
    
    
    public ProductResponse(Long productId, String productName, String description, String category, Double price,
			Integer quantity, String imageUrl) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.category = category;
		this.price = price;
		this.quantity = quantity;
		this.imageUrl = imageUrl;
	}
    
    
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	@Override
	public String toString() {
		return "ProductResponse [productId=" + productId + ", productName=" + productName + ", description="
				+ description + ", category=" + category + ", price=" + price + ", quantity=" + quantity + ", imageUrl="
				+ imageUrl + "]";
	}
	

    
    
}
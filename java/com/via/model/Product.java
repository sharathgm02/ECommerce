package com.via.model;

import com.via.product.ProductStatus;
import java.util.Date;

public class Product {

	private long productID;
	private long categoryID;
	
	private String productName;
	private String imageUrl;
	private double unitPrice;
	
	private int quantity;
	private String author;
	private String summary;
	
	private Date creationTime;
	private ProductStatus status;
	
	private transient ProductDetail productDetail;
	

	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public long getProductID() {
		return productID;
	}
	
	public void setProductID(long productID) {
		this.productID = productID;
	}
	
	public long getCategoryID() {
		return categoryID;
	}
	
	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	public ProductStatus getStatus() {
		return status;
	}
	
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	
	
	
}

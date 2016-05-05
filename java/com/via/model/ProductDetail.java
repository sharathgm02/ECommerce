package com.via.model;

public class ProductDetail {

	private long productDetailID;
	private long productID;
	
	private String description;
	private String imageUrls;

	
	public long getProductDetailID() {
		return productDetailID;
	}
	
	public void setProductDetailID(long productDetailID) {
		this.productDetailID = productDetailID;
	}
	
	public long getProductID() {
		return productID;
	}
	
	public void setProductID(long productID) {
		this.productID = productID;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImageUrls() {
		return imageUrls;
	}
	
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

}


package com.via.model;

public class Category {

	private long categoryID;
	private String categoryName;
	private String categoryDescription;
	
	public long getCategoryID() {
		return categoryID;
	}
	
	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getCategoryDescription() {
		return categoryDescription;
	}
	
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
}

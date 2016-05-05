package com.via.model;

import java.util.Date;

import com.via.order.OrderStatus;

public class OrderDetail {

	private long orderDetailID;
	private long orderID;
	private long productID;
	
	private double unitPrice;
	private int quantity;
	
	private Date creationTime;
	private OrderStatus status;
	
	
	public long getOrderDetailID() {
		return orderDetailID;
	}
	
	public void setOrderDetailID(long orderDetailID) {
		this.orderDetailID = orderDetailID;
	}
	
	public long getOrderID() {
		return orderID;
	}
	
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	
	public long getProductID() {
		return productID;
	}
	
	public void setProductID(long productID) {
		this.productID = productID;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
}

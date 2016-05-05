package com.via.model;

import java.util.Date;
import java.util.List;

import com.via.order.OrderStatus;

public class Order {

	private long orderID;
	private long customerID;
	
	private double totalAmount;
	private String address;
	
	private Date creationTime;
	private OrderStatus status;
	
	private transient List<OrderDetail> orderDetailList;
	
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public long getOrderID() {
		return orderID;
	}
	
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	
	public long getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
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

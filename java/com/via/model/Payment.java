package com.via.model;

import java.util.Date;

import com.via.payment.PaymentManager;

public class Payment {

	private long paymentID;
	private long customerID;
	private long orderID;
	
	private short paymentMode;
	private double totalAmount;
	
	private Date paymentTime;
	private PaymentManager status;
	
	
	public long getPaymentID() {
		return paymentID;
	}
	
	public void setPaymentID(long paymentID) {
		this.paymentID = paymentID;
	}
	
	public long getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	
	public long getOrderID() {
		return orderID;
	}
	
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	
	public short getPaymentMode() {
		return paymentMode;
	}
	
	public void setPaymentMode(short paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Date getPaymentTime() {
		return paymentTime;
	}
	
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	
	public PaymentManager getStatus() {
		return status;
	}
	
	public void setStatus(PaymentManager status) {
		this.status = status;
	}
	
}

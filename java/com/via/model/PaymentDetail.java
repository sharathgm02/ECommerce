package com.via.model;

import java.util.Date;

import com.via.payment.PaymentManager;

public class PaymentDetail {

	private long paymentDetailID;
	private long paymentID;
	
	private short type;
	private double amount;
	private Date creationTime;
	
	private long transactionID;
	private PaymentManager status;
	
	
	public long getPaymentDetailID() {
		return paymentDetailID;
	}
	
	public void setPaymentDetailID(long paymentDetailID) {
		this.paymentDetailID = paymentDetailID;
	}
	
	public long getPaymentID() {
		return paymentID;
	}
	
	public void setPaymentID(long paymentID) {
		this.paymentID = paymentID;
	}
	
	public short getType() {
		return type;
	}
	
	public void setType(short type) {
		this.type = type;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Date getCreationTime() {
		return creationTime;
	}
	
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	public long getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(long transactionID) {
		this.transactionID = transactionID;
	}
	
	public PaymentManager getStatus() {
		return status;
	}
	
	public void setStatus(PaymentManager status) {
		this.status = status;
	}
	
}

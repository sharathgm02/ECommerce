package com.via.dao;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {

	@SuppressWarnings("rawtypes")
	private static Map<Class, Object> cacheMap = new HashMap<Class, Object>();

	public static ProductDao getProductDao() {
		ProductDao productObj = (ProductDao) cacheMap.get(ProductDao.class);
		if (productObj == null) {
			productObj = new ProductDao();
			cacheMap.put(ProductDao.class, productObj);
		}
		return productObj;
	}

	public static CategoryDao getCategoryDao() {
		CategoryDao categoryObj = (CategoryDao) cacheMap.get(CategoryDao.class);
		if (categoryObj == null) {
			categoryObj = new CategoryDao();
			cacheMap.put(CategoryDao.class, categoryObj);
		}
		return categoryObj;
	}

	public static CustomerDao getCustomerDao() {
		CustomerDao customerObj = (CustomerDao) cacheMap.get(CustomerDao.class);
		if (customerObj == null) {
			customerObj = new CustomerDao();
			cacheMap.put(CustomerDao.class, customerObj);
		}
		return customerObj;
	}

	public static OrderDao getOrderDao() {
		OrderDao orderObj = (OrderDao) cacheMap.get(OrderDao.class);
		if (orderObj == null) {
		    orderObj = new OrderDao();
		    cacheMap.put(OrderDao.class, orderObj); 
		}
		return orderObj;
	}

	public static OrderDetailDao getOrderDetailDao() {
		OrderDetailDao orderDetailObj = (OrderDetailDao) cacheMap.get(OrderDetailDao.class);
		if (orderDetailObj == null) {
			orderDetailObj = new OrderDetailDao();
			cacheMap.put(OrderDetailDao.class, orderDetailObj);
		}
		return orderDetailObj;
	}

	public static PaymentDao getPaymentDao() {
		PaymentDao paymentObj = (PaymentDao) cacheMap.get(PaymentDao.class);
		if (paymentObj == null) {
			paymentObj = new PaymentDao();
			cacheMap.put(PaymentDao.class, paymentObj);
		}
		return paymentObj;
	}

	public static PaymentDetailDao getPaymentDetailDao() {
		PaymentDetailDao paymentDetailObj = (PaymentDetailDao) cacheMap.get(PaymentDetailDao.class);
		if (paymentDetailObj == null) {
			paymentDetailObj = new PaymentDetailDao();
			cacheMap.put(PaymentDetailDao.class, paymentDetailObj);
		}
		return paymentDetailObj;
	}

	public static ProductDetailDao getProductDetailDao() {
		ProductDetailDao productDetailObj = (ProductDetailDao) cacheMap.get(ProductDetailDao.class);
		if (productDetailObj == null) {
			productDetailObj = new ProductDetailDao();
			cacheMap.put(ProductDetailDao.class, productDetailObj);
		}
		return productDetailObj;
	}

	public static ShippingAddressDao getShippingAddressDao() {
		ShippingAddressDao addressObj = (ShippingAddressDao) cacheMap.get(ShippingAddressDao.class);
		if (cacheMap.get(ShippingAddressDao.class) == null) {
			addressObj = new ShippingAddressDao();
			cacheMap.put(ShippingAddressDao.class, addressObj);
		}
		return addressObj;
	}

}

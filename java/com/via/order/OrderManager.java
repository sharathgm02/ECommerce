package com.via.order;

import java.util.List;

import com.via.dao.DaoException;
import com.via.dao.DaoFactory;
import com.via.model.Order;
import com.via.model.OrderDetail;

public class OrderManager {

	public static Order createOrder(Order order) throws DaoException {

		return DaoFactory.getOrderDao().createOrder(order);
	}

	public static OrderDetail createOrderDetail(OrderDetail orderDetail)
			throws DaoException {

		return DaoFactory.getOrderDetailDao().createOrderDetail(orderDetail);
	}

	public static Order getOrderById(long orderId, boolean loadOrderDetails)
			throws DaoException {

		if (orderId <= 0) {
			return null;
		}
		Order order = DaoFactory.getOrderDao().getOrderById(orderId);
		if (order!=null && loadOrderDetails) {
			order.setOrderDetailList(getOrderDetailsByOrderId(orderId));
		}
		return order;
	}

	public static List<OrderDetail> getOrderDetailsByOrderId(long orderId)
			throws DaoException {
		
		if(orderId<=0){
			return null;
		}
		return DaoFactory.getOrderDetailDao().getOrderDetailsByOrderId(orderId);
	}

	public static boolean updateOrderStatus(Order order, OrderStatus status) throws DaoException {

		return DaoFactory.getOrderDao().updateOrderStatus(order.getOrderID(), status);
	}
	
	public static boolean updateOrderDetailStatus(OrderDetail orderDetail, OrderStatus status) throws DaoException{
		
		return DaoFactory.getOrderDetailDao().updateOrderDetailStatus(orderDetail.getOrderDetailID(), status);
	}

}

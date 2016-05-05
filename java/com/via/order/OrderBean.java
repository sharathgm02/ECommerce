package com.via.order;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.via.controller.Action;
import com.via.controller.Attribute;
import com.via.customer.CustomerManager;
import com.via.dao.DaoException;
import com.via.model.Customer;
import com.via.model.Order;
import com.via.model.OrderDetail;
import com.via.model.Product;
import com.via.product.ProductManager;
import com.via.util.StringUtility;
import com.via.util.UrlUtility;

public class OrderBean {

	private static final int MAX_QUANTITY_ALLOWED = 10;

	public static String getConfirmationUrl(HttpServletRequest request) {

		return UrlUtility.getActionUrl(request, Action.CONFIRMATION);
	}

	public static String getOrderUrl(HttpServletRequest request) {

		return UrlUtility.getActionUrl(request, Action.ORDER);
	}

	public static String getOrderDetailUrl(HttpServletRequest request,
			long orderId) {

		return UrlUtility.getActionUrl(request,
				new String[] { Long.toString(orderId) }, Action.ORDER_DETAILS);
	}

	public static long createOrder(HttpServletRequest request)
			throws DaoException {

		int quantity = StringUtility.parseToInt(
				request.getParameter("quantity"), -1);

		Product product = ProductManager.getProductById(StringUtility
				.parseToLong(request.getParameter("productId"), -1l),
				false);

		String email = request.getParameter("email").toLowerCase();

		if (product != null && quantity <= MAX_QUANTITY_ALLOWED
				&& quantity <= product.getQuantity()
				&& StringUtility.isValidEmail(email)) {

			Customer customer = CustomerManager.getCustomerByEmail(email);

			if (customer == null) {
				customer = new Customer();
				customer.setEmail(email);
				CustomerManager.createCustomer(customer);
			}

			Order order = new Order();

			order.setCustomerID(customer.getCustomerID());
			order.setTotalAmount(product.getUnitPrice() * quantity);

			order.setAddress(request.getParameter("address"));
			order.setCreationTime(new Date());
			order.setStatus(OrderStatus.PROCESSING);

			OrderManager.createOrder(order);

			
			OrderDetail orderDetail = new OrderDetail();

			orderDetail.setOrderID(order.getOrderID());
			orderDetail.setProductID(product.getProductID());
			orderDetail.setUnitPrice(product.getUnitPrice());

			orderDetail.setQuantity(quantity);
			orderDetail.setCreationTime(new Date());
			orderDetail.setStatus(OrderStatus.PROCESSING);

			OrderManager.createOrderDetail(orderDetail);

			if (!(ProductManager.updateQuantity(product.getProductID(), quantity) && updateOrderStatus(order, orderDetail, OrderStatus.APPROVED))) {
				return 0;
			}

			return order.getOrderID();
		}
		return 0;
	}

	public static boolean getOrderById(HttpServletRequest request,
			long orderId, boolean loadOrderDetails) throws DaoException {

		Order order = OrderManager.getOrderById(orderId, loadOrderDetails);

		if (order != null) {
			request.setAttribute(Attribute.ORDER.toString(), order);
			return true;
		}

		return false;
	}

	public static boolean updateOrderStatus(Order order, OrderDetail orderDetail, OrderStatus status) throws DaoException {

		if (OrderManager.updateOrderStatus(order, status) && OrderManager.updateOrderDetailStatus(orderDetail, status)){
			return true;
		}
		return false;
	}

}

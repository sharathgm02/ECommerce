package com.via.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.via.dao.DaoException;
import com.via.dbManager.DatabaseManager;
import com.via.order.OrderBean;
import com.via.product.ProductBean;
import com.via.product.ProductManager;
import com.via.util.EnumUtility;
import com.via.util.StringUtility;
import com.via.util.UrlUtility;

public class ECommerceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String HOME_JSP = "/home.jsp";
	private static final String PRODUCT_DETAILS_JSP = "/product.jsp";
	private static final String ERROR_JSP = "/error.jsp";
	private static final String CATEGORY_JSP = "/category.jsp";
	private static final String ORDER_JSP = "/order.jsp";
	private static final String ORDER_DETAILS_JSP = "/order_details.jsp";
	

	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
			ProductManager.initialize();
		} catch (DaoException e) {
			e.printStackTrace();
		} finally {
			try {
				DatabaseManager.closeConnection();
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String url = request.getServletPath();

		String[] urlParameters = url.split("/");

		int index = 1;

		Action action = EnumUtility.parseEnum(
				UrlUtility.getRequestUrlParameter(urlParameters, index++),
				Action.class, Action.HOME);
		try {

			switch (action) {

			case HOME:

				if (ProductBean.getAllProducts(request)) {
					request.getRequestDispatcher(HOME_JSP).forward(request,
							response);
				} else {
					request.getRequestDispatcher(ERROR_JSP).forward(request,
							response);
				}
				break;

			case CATEGORY:

				if (ProductBean.getProductByCategory(request, StringUtility
						.parseToLong(UrlUtility.getRequestUrlParameter(
								urlParameters, index++), -1l))) {
					request.getRequestDispatcher(CATEGORY_JSP).forward(request,
							response);
				} else {
					request.getRequestDispatcher(HOME_JSP).forward(request,
							response);
				}
				break;

			case PRODUCT:

				if (ProductBean.getProductById(request, StringUtility
						.parseToLong(UrlUtility.getRequestUrlParameter(
								urlParameters, index++), -1l), true)) {
					request.getRequestDispatcher(PRODUCT_DETAILS_JSP).forward(
							request, response);
				} else {
					request.getRequestDispatcher(ERROR_JSP).forward(request,
							response);
				}
				break;

			case ORDER:

				if (ProductBean.addToCart(request)) {
					request.getRequestDispatcher(ORDER_JSP).forward(request,
							response);
				} else {
					request.getRequestDispatcher(ERROR_JSP).forward(request,
							response);
				}
				break;

			case CONFIRMATION:

				long orderId = OrderBean.createOrder(request);
				if (orderId > 0) {
					response.sendRedirect(OrderBean.getOrderDetailUrl(request,
							orderId));
				} else {
					request.getRequestDispatcher(ERROR_JSP).forward(request,
							response);
				}
				break;

			case ORDER_DETAILS:
				orderId = StringUtility.parseToLong(UrlUtility
						.getRequestUrlParameter(urlParameters, index++), -1l);

				if (OrderBean.getOrderById(request, orderId, true)) {
					request.getRequestDispatcher(ORDER_DETAILS_JSP).forward(
							request, response);
				} else {
					request.getRequestDispatcher(ERROR_JSP).forward(request,
							response);
				}

				break;

			case STATIC:
				request.getRequestDispatcher("default").forward(request,
						response);
				break;

			}

		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(ERROR_JSP).forward(request, response);
		} finally {
			try {
				DatabaseManager.closeConnection();
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
	}
}

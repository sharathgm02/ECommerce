package com.via.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.via.controller.Action;
import com.via.controller.Attribute;
import com.via.dao.DaoException;
import com.via.model.Category;
import com.via.model.Product;
import com.via.util.StringUtility;
import com.via.util.UrlUtility;

public class ProductBean {

	private static final int MAX_QUANTITY_ALLOWED = 10;

	public static boolean getAllProducts(HttpServletRequest request)
			throws DaoException {

		List<Product> products = ProductManager.getAllProducts();
		if (products != null) {
			request.setAttribute(Attribute.ALL_PRODUCTS.toString(), products);
			return true;
		}
		return false;
	}

	public static boolean getProductById(HttpServletRequest request,
			long productId, boolean loadProductDetails) throws DaoException {

		Product product = ProductManager.getProductById(productId, loadProductDetails);

		if (product != null) {
			request.setAttribute(Attribute.PRODUCT_VIEW.toString(), product);
			return true;
		}
		return false;
	}

	public static boolean getProductByCategory(HttpServletRequest request,
			long catId) throws DaoException {

		List<Product> products = ProductManager.getProductsByCategory(catId);
		if (products != null) {
			request.setAttribute(Attribute.ALL_PRODUCTS.toString(), products);
			return true;
		}
		return false;
	}

	public static boolean addToCart(HttpServletRequest request)
			throws DaoException {

		int quantity = StringUtility.parseToInt(
				request.getParameter("quantity"), -1);

		Product product = ProductManager.getProductById(StringUtility
				.parseToLong(request.getParameter("productId"), -1l), false);

		if (quantity <= MAX_QUANTITY_ALLOWED && product != null
				&& quantity <= product.getQuantity()) {
			return true;
		}

		return false;
	}

	public static String getProductUrl(HttpServletRequest request,
			Product product) {

		return UrlUtility.getActionUrl(request,
				new String[] { Long.toString(product.getProductID()) },
				Action.PRODUCT);
	}

	public static String getCategoryUrl(HttpServletRequest request,
			Category category) {
		return UrlUtility.getActionUrl(request,
				new String[] { Long.toString(category.getCategoryID()) },
				Action.CATEGORY);
	}

}
package com.via.product;

import java.util.List;
import java.util.Map;

import com.via.dao.DaoException;
import com.via.dao.DaoFactory;
import com.via.model.Category;
import com.via.model.Product;
import com.via.model.ProductDetail;

public class ProductManager {

	private static Map<Long, Category> s_categories = null;

	public static void initialize() throws DaoException {
		s_categories = (Map<Long, Category>) DaoFactory.getCategoryDao()
				.getAllCategories();
	}

	public static Product getProductById(long productId,
			boolean loadProductDetails) throws DaoException {
		if (productId <= 0) {
			return null;
		}
		Product product = DaoFactory.getProductDao().getProductById(productId);
		if (product != null && loadProductDetails) {
			product.setProductDetail(getProductDetailById(productId));
		}
		return product;
	}

	public static ProductDetail getProductDetailById(long productId)
			throws DaoException {
		if (productId <= 0) {
			return null;
		}
		return DaoFactory.getProductDetailDao().getProductDetailById(productId);
	}

	public static List<Product> getAllProducts() throws DaoException {

		return DaoFactory.getProductDao().getAllProducts();
	}

	public static boolean updateQuantity(long productId, int quantity)
			throws DaoException {
		return DaoFactory.getProductDao().updateQuantity(productId, quantity);
	}

	public static List<Product> getProductsByCategory(long categoryId)
			throws DaoException {
		if (categoryId <= 0) {
			return null;
		}
		return DaoFactory.getProductDao().getProductsByCategory(categoryId);
	}

	public static Map<Long, Category> getAllCategories() throws DaoException {
		return s_categories;
	}
}

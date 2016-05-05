package com.via.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.via.dbManager.DatabaseManager;
import com.via.model.Product;
import com.via.product.ProductStatus;

public class ProductDao {

	private static final String SQL_ALL_PRODUCTS = "SELECT * FROM Products ORDER BY creationTime DESC LIMIT 6";
	private static final String SQL_PROD_BY_ID = "SELECT * FROM Products WHERE productId = ?";
	private static final String SQL_PROD_BY_CATEGORY = "SELECT * FROM Products WHERE categoryId = ? ORDER BY creationTime DESC LIMIT 9";
	private static final String SQL_UPDATE_AVILABLE_COUNT = "UPDATE Products SET quantity = quantity - ? WHERE productId = ? AND quantity >= ?";

	public List<Product> getAllProducts() throws DaoException {

		Connection con = null;
		Statement statement = null;
		ResultSet result = null;

		List<Product> productList = new ArrayList<Product>();

		try {
			con = DatabaseManager.getConnection();
			statement = con.createStatement();
			result = statement.executeQuery(SQL_ALL_PRODUCTS);

			while (result.next()) {
				productList.add(createProductSet(result));
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}

		return productList;
	}

	public Product getProductById(long productID) throws DaoException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			con = DatabaseManager.getConnection();
			statement = con.prepareStatement(SQL_PROD_BY_ID);
			statement.setLong(1, productID);
			result = statement.executeQuery();

			if (result.next()) {
				return createProductSet(result);
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}

		return null;
	}

	public List<Product> getProductsByCategory(long categoryId)
			throws DaoException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Product> productList = new ArrayList<Product>();

		try {
			con = DatabaseManager.getConnection();
			statement = con.prepareStatement(SQL_PROD_BY_CATEGORY);
			statement.setLong(1, categoryId);

			result = statement.executeQuery();
			while (result.next()) {
				productList.add(createProductSet(result));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}

		return productList;

	}

	public boolean updateQuantity(long productId, int quantity)
			throws DaoException {

		Connection con = null;
		PreparedStatement statement = null;

		try {
			con = DatabaseManager.getConnection();
			statement = con.prepareStatement(SQL_UPDATE_AVILABLE_COUNT);

			statement.setInt(1, quantity);
			statement.setLong(2, productId);
			statement.setInt(3, quantity);

			return (statement.executeUpdate() > 0);
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(null, statement);
		}
	}

	private static Product createProductSet(ResultSet result)
			throws DaoException {

		Product product = null;

		try {

			product = new Product();
			long productId = result.getLong("productId");

			product.setProductID(productId);
			product.setCategoryID(result.getLong("categoryid"));
			product.setProductName(result.getString("productname"));

			product.setImageUrl(result.getString("imageUrl"));
			product.setUnitPrice(result.getDouble("unitprice"));
			product.setQuantity(result.getInt("quantity"));
			product.setAuthor(result.getString("author"));

			product.setSummary(result.getString("summary"));
			product.setCreationTime(result.getTimestamp("creationtime"));
			product.setStatus(ProductStatus.values()[result.getShort("status")]);

		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return product;
	}

}

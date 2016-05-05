package com.via.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import com.via.dbManager.DatabaseManager;
import com.via.model.ProductDetail;

public class ProductDetailDao {
	
	private static final String SQL_PRODUCT_DETAIL = "SELECT * FROM productDetails WHERE productID = ?";
	
	public ProductDetail getProductDetailById(long productID) throws DaoException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			con = DatabaseManager.getConnection();
			statement = con.prepareStatement(SQL_PRODUCT_DETAIL);
			statement.setLong(1, productID);
			result = statement.executeQuery();
			
			if (result.next()) {
				return createProductDetailSet(result);
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}
		return null;
	}

	private static ProductDetail createProductDetailSet(ResultSet result)
			throws DaoException {

		ProductDetail productDetail = null;

		try {

			productDetail = new ProductDetail();

			productDetail.setProductID(result.getLong("productID"));
			productDetail.setProductDetailID(result.getLong("productDetailId"));
			productDetail.setDescription(result.getString("description"));
			productDetail.setImageUrls(result.getString("imageUrls"));
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return productDetail;

	}

}

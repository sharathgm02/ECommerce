package com.via.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import com.via.dbManager.DatabaseManager;
import com.via.model.Category;

public class CategoryDao {

	private static final String SQL_ALL_CATEGORIES = "SELECT * FROM categories ORDER BY categoryName";
	
	public Map<Long, Category> getAllCategories() throws DaoException {

		Connection con = null;
		Statement statement = null;
		ResultSet result = null;

		Map<Long, Category> categoryList = new LinkedHashMap<Long, Category>();

		try {
			con = DatabaseManager.getConnection();
			statement = con.createStatement();
			result = statement.executeQuery(SQL_ALL_CATEGORIES);

			while (result.next()) {
				Category obj = createCategorySet(result);
				categoryList.put(obj.getCategoryID(),obj);
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}

		return categoryList;
	}

	
	private static Category createCategorySet(ResultSet result)
			throws DaoException {

		Category category = null;

		try {

			category = new Category();
			category.setCategoryID(result.getLong("categoryid"));
			category.setCategoryName(result.getString("categoryname"));
			category.setCategoryDescription(result.getString("categorydescription"));

		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return category;

	}

	
}

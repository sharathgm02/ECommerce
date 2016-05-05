package com.via.dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.via.dao.DaoException;

public class DatabaseManager {

	private static final String URL = "jdbc:postgresql://localhost:5433/postgres";
	private static final String USERNAME = "postres";
	private static final String PASSWORD = "password";

	private static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

	public static Connection getConnection() throws DaoException {

		try {
			Connection con = null;
			if (threadConnection.get() == null) {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				threadConnection.set(con);
			}

			return threadConnection.get();
		}

		catch (SQLException e) {
			throw new DaoException(e);
		} catch (ClassNotFoundException e) {
			throw new DaoException(e);
		}

	}

	public static void close(ResultSet resultSet, Statement statement)
			throws DaoException {

		try {

			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		}

	}

	public static void closeConnection() throws DaoException {
		Connection con = threadConnection.get();
		if (con != null) {
			try {
				threadConnection.set(null);
				con.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

}
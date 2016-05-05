package com.via.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.via.dbManager.DatabaseManager;
import com.via.model.Order;
import com.via.order.OrderStatus;

public class OrderDao {

	private static final String SQL_CREATE_ORDER = "INSERT INTO Orders (orderId, customerId, totalAmount, address, creationTime, status) VALUES (?,?,?,?,?,?)";
	private static final String SQL_GENERATE_ORDER_ID = "SELECT NEXTVAL ('orders_sequence')";
	private static final String SQL_ORDER_BY_ID = "SELECT * FROM orders WHERE orderId = ?";
	private static final String SQL_UPDATE_ORDER = "UPDATE Orders SET status = ? WHERE orderId = ?";

	public Order createOrder(Order order) throws DaoException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			con = DatabaseManager.getConnection();

			statement = con.prepareStatement(SQL_GENERATE_ORDER_ID);
			result = statement.executeQuery();
			result.next();
			long orderId = result.getLong("nextval");

			statement = con.prepareStatement(SQL_CREATE_ORDER);
			statement.setLong(1, orderId);
			statement.setLong(2, order.getCustomerID());
			statement.setDouble(3, order.getTotalAmount());

			statement.setString(4, order.getAddress());
			statement.setTimestamp(5, new Timestamp(order.getCreationTime()
					.getTime()));
			statement.setShort(6, (short) order.getStatus().ordinal());
			statement.executeUpdate();

			order.setOrderID(orderId);

			return order;

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}

	}

	public Order getOrderById(long orderId) throws DaoException {
		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Order order = null;

		try {
			con = DatabaseManager.getConnection();
			statement = con.prepareStatement(SQL_ORDER_BY_ID);
			statement.setLong(1, orderId);
			result = statement.executeQuery();

			if (result.next()) {

				order = new Order();
				order.setOrderID(result.getLong("orderId"));
				order.setCustomerID(result.getLong("customerId"));
				order.setTotalAmount(result.getDouble("totalAmount"));

				order.setAddress(result.getString("address"));
				order.setCreationTime(result.getTimestamp("creationtime"));
				order.setStatus(OrderStatus.values()[result.getShort("status")]);

			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}

		return order;
	}

	public boolean updateOrderStatus(long orderId, OrderStatus status)
			throws DaoException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DatabaseManager.getConnection();
			stmt = con.prepareStatement(SQL_UPDATE_ORDER);
			stmt.setShort(1, (short) status.ordinal());
			stmt.setLong(2, orderId);
			return (stmt.executeUpdate() > 0);

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(null, stmt);
		}
	}

}

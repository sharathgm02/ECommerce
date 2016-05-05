package com.via.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.via.dbManager.DatabaseManager;
import com.via.model.OrderDetail;
import com.via.order.OrderStatus;

public class OrderDetailDao {

	private static final String SQL_ORDER_DETAILS_BY_ID = "SELECT * FROM OrderDetails WHERE orderId = ?";
	private static final String SQL_GENERATE_ORDER_DETAIL_ID = "SELECT NEXTVAL ('order_details_sequence')";
	private static final String SQL_CREATE_ORDER_DETAILS = "INSERT INTO OrderDetails(detailId, orderId, productId, unitPrice, quantity, creationTime, status) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE_ORDER_DETAIL = "UPDATE OrderDetails SET status = ? WHERE detailId = ?";

	public OrderDetail createOrderDetail(OrderDetail orderDetail)
			throws DaoException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			con = DatabaseManager.getConnection();
			statement = con.prepareStatement(SQL_GENERATE_ORDER_DETAIL_ID);
			result = statement.executeQuery();
			result.next();
			long orderDetailId = result.getLong("nextval");

			statement = con.prepareStatement(SQL_CREATE_ORDER_DETAILS);
			statement.setLong(1, orderDetailId);
			statement.setLong(2, orderDetail.getOrderID());
			statement.setLong(3, orderDetail.getProductID());

			statement.setDouble(4, orderDetail.getUnitPrice());
			statement.setInt(5, orderDetail.getQuantity());
			statement.setTimestamp(6, new Timestamp(orderDetail
					.getCreationTime().getTime()));

			statement.setShort(7, (short) orderDetail.getStatus().ordinal());
			statement.executeUpdate();

			orderDetail.setOrderDetailID(orderDetailId);
			return orderDetail;

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}

	}

	public List<OrderDetail> getOrderDetailsByOrderId(long orderId)
			throws DaoException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

		try {
			con = DatabaseManager.getConnection();
			statement = con.prepareStatement(SQL_ORDER_DETAILS_BY_ID);
			statement.setLong(1, orderId);

			result = statement.executeQuery();
			while (result.next()) {
				orderDetailList.add(createOrderDetailSet(result));
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}

		return orderDetailList;

	}

	private static OrderDetail createOrderDetailSet(ResultSet result)
			throws DaoException {

		OrderDetail orderDetail = null;

		try {

			orderDetail = new OrderDetail();

			orderDetail.setOrderDetailID(result.getLong("detailId"));
			orderDetail.setOrderID(result.getLong("orderId"));
			orderDetail.setProductID(result.getLong("productId"));
			orderDetail.setUnitPrice(result.getDouble("unitPrice"));
			orderDetail.setQuantity(result.getInt("quantity"));
			orderDetail.setCreationTime(result.getTimestamp("creationTime"));
			orderDetail.setStatus(OrderStatus.values()[result
					.getShort("status")]);

		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return orderDetail;

	}

	public boolean updateOrderDetailStatus(long orderDetailId,
			OrderStatus status) throws DaoException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DatabaseManager.getConnection();
			stmt = con.prepareStatement(SQL_UPDATE_ORDER_DETAIL);
			stmt.setShort(1, (short) status.ordinal());
			stmt.setLong(2, orderDetailId);

			if (stmt.executeUpdate() > 0) {
				return true;
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(null, stmt);
		}
		return false;
	}

}

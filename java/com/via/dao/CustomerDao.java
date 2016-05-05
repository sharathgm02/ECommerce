package com.via.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.via.customer.CustomerStatus;
import com.via.dbManager.DatabaseManager;
import com.via.model.Customer;

public class CustomerDao {

	private static String SQL_CUSTOMER_BY_EMAIL = "SELECT * FROM Customers WHERE email=?";
	private static String SQL_INSERT_CUSTOMER = "INSERT INTO Customers(customerId, email, creationTime, status) VALUES (?,?,CURRENT_TIMESTAMP(0),?)";
	private static String SQL_GENERATE_CUSTOMER_ID = "SELECT NEXTVAL ('customer_sequence')";

	public Customer getCustomerByEmail(String email) throws DaoException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Customer customer = null;
		try {
			con = DatabaseManager.getConnection();
			statement = con.prepareStatement(SQL_CUSTOMER_BY_EMAIL);
			statement.setString(1, email);
			result = statement.executeQuery();
			if (result.next()) {
				return createCustomerSet(result);
			}

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}
		return customer;
	}

	public Customer createCustomer(Customer customer) throws DaoException {

		Connection con = null;
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			con = DatabaseManager.getConnection();

			statement = con.prepareStatement(SQL_GENERATE_CUSTOMER_ID);
			result = statement.executeQuery();
			result.next();
			long customerId = result.getLong("nextval");

			statement = con.prepareStatement(SQL_INSERT_CUSTOMER);
			statement.setLong(1, customerId);
			statement.setString(2, customer.getEmail());
			statement.setShort(3, (short) CustomerStatus.ACTIVE.ordinal());
			statement.executeUpdate();
			
			customer.setCustomerID(customerId);
			return customer;

		} catch (SQLException e) {
			throw new DaoException(e);
		} finally {
			DatabaseManager.close(result, statement);
		}

	}

	private Customer createCustomerSet(ResultSet result) throws DaoException {

		Customer customer = null;

		try {
			customer = new Customer();
			customer.setCustomerID(result.getLong("customerId"));
			customer.setName(result.getString("name"));
			customer.setEmail(result.getString("email"));
			customer.setPhone(result.getString("phone"));
			customer.setPassword(result.getString("password"));
			customer.setCreationTime(result.getDate("creationTime"));
			customer.setLastLogin(result.getDate("lastLogin"));
			customer.setStatus(CustomerStatus.values()[result
					.getShort("status")]);
		} catch (SQLException e) {
			throw new DaoException(e);
		}

		return customer;

	}

}

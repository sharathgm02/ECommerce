package com.via.customer;

import com.via.dao.DaoException;
import com.via.dao.DaoFactory;
import com.via.model.Customer;

public class CustomerManager {

	public static Customer getCustomerByEmail(String email) throws DaoException {

		if(email==null){
			return null;
		}
		return DaoFactory.getCustomerDao().getCustomerByEmail(email);

	}

	public static Customer createCustomer(Customer customer)
			throws DaoException {

		return DaoFactory.getCustomerDao().createCustomer(customer);

	}

}

package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

public class CustomerDao implements AutoCloseable {
	private Connection connection;

	public CustomerDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	public void insertCustomer(Customer customer, Scanner sc) throws SQLException {

		String sql = "INSERT INTO customer(name,email,password,mobile) VALUES(?,?,?,?)";
		try (PreparedStatement insertStatement = connection.prepareCall(sql)) {
			customer.acceptCustomer(sc);
			insertStatement.setString(1, customer.getName());
			insertStatement.setString(2, customer.getEmail());
			insertStatement.setString(3, customer.getPassword());
			insertStatement.setString(4, customer.getMobile());
			insertStatement.executeUpdate();
		}
		System.out.println("Succesfully Registered.. :)");
	}

	public Customer loginCustomer(String email, String password) throws SQLException {

		String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
		try (PreparedStatement selectStatement = connection.prepareStatement(sql)) {
			selectStatement.setString(1, email);
			selectStatement.setString(2, password);
			ResultSet rs = selectStatement.executeQuery();

			if (rs.next()) {
				Customer customer = new Customer();
				customer.setCid(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setEmail(email);
				customer.setPassword(password);
				customer.setMobile(rs.getString(5));
				return customer;
			}
		}
		return null;
	}
	
	
	public List<Customer> DisplayAllCustomers() throws SQLException {

		String sql = "SELECT * FROM customer";
		List<Customer> customerList =  new ArrayList<Customer>();
		try (PreparedStatement selectStatement = connection.prepareStatement(sql)) {
			ResultSet rs = selectStatement.executeQuery();

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCid(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setEmail(rs.getString(3));
				customer.setPassword(rs.getString(4));
				customer.setMobile(rs.getString(5));
				customerList.add(customer);
			}
			return customerList;
		}
	}

	
	
	@Override
	public void close() throws SQLException {
		if (connection != null)
			connection.close();
	}

}

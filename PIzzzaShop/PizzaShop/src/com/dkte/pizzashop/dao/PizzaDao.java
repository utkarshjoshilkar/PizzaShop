package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;
import com.mysql.cj.protocol.Resultset;

public class PizzaDao implements AutoCloseable {
	private static Connection connection;

	public PizzaDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	public static void AddPizza(Pizza newPizza) throws SQLException {
		String sql = "INSERT INTO menu (mid,name,description,price) VALUES (?,?,?,?)";
		try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
			insertStatement.setInt(1, newPizza.getMid());
			insertStatement.setString(2, newPizza.getName());
			insertStatement.setString(3, newPizza.getDescription());
			insertStatement.setDouble(4, newPizza.getPrice());
			insertStatement.executeUpdate();
		} 
	}

	public static void UpdatePrice(int mid,double price) throws SQLException {
		String sql = "UPDATE menu SET price = ? WHERE mid = ?";
		try (PreparedStatement updatestatement = connection.prepareStatement(sql)) {
			updatestatement.setDouble(1, price);
			updatestatement.setInt(2, mid);
			updatestatement.executeUpdate();
		}
	}
	
	public List<Pizza> getAllPizza() throws SQLException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		String sql = "SELECT * FROM menu";
		try (PreparedStatement getpizzastatement = connection.prepareStatement(sql)) {
			ResultSet rs = getpizzastatement.executeQuery();
			while (rs.next()) {
				Pizza pizzaCurrent = new Pizza();
				pizzaCurrent.setMid(rs.getInt(1));
				pizzaCurrent.setName(rs.getString(2));
				pizzaCurrent.setDescription(rs.getString(3));
				pizzaCurrent.setPrice(rs.getInt(4));
				pizzaList.add(pizzaCurrent);
			}
			return pizzaList;
		}
	}
	
	

	@Override
	public void close() throws Exception {
		if (connection != null) {
			connection.close();
		}
	}
}

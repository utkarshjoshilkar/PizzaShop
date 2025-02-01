package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtil;

public class OrderDao implements AutoCloseable {
	private static Connection connection;

	public OrderDao() throws SQLException {
		connection = DBUtil.getConnection();
	}

	public void PlaceOrder(int cid, int mid) throws SQLException {

		String sql = "INSERT INTO orders (cid,mid) VALUES(?,?)";
		try (PreparedStatement orderStatement = connection.prepareCall(sql)) {
			orderStatement.setInt(1, cid);
			orderStatement.setInt(2, mid);
			orderStatement.executeUpdate();
		}
		System.out.println("Succesfully Ordered :)");
	}

	
	
	public List<Pizza> OrderHistry(int cid) {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		String sql = "SELECT m.* FROM menu m INNER JOIN orders o ON m.mid = o.mid WHERE o.cid = ?;";
		try (PreparedStatement selectStatement = connection.prepareStatement(sql)) {
			selectStatement.setInt(1,cid);
			ResultSet rs = selectStatement.executeQuery();

			while(rs.next()) {
				Pizza pizzaCurrent = new Pizza();
				pizzaCurrent.setMid(rs.getInt(1));
				pizzaCurrent.setName(rs.getString(2));
				pizzaCurrent.setDescription(rs.getString(3));
				pizzaCurrent.setPrice(rs.getInt(4));
				pizzaList.add(pizzaCurrent);
			}
			return pizzaList;
		} catch (SQLException e) {
				e.printStackTrace();
		}
		return null;
	}
	
	public List<Pizza> getAllOrders() throws SQLException {
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		String sql = "SELECT m.* FROM menu m INNER JOIN orders o ON m.mid = o.mid";
		try (PreparedStatement getallpizzastatement = connection.prepareStatement(sql)) {
			ResultSet rs = getallpizzastatement.executeQuery();
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
	
	public static List<Double> getTotalProfit() throws SQLException {
		List<Double> priceList = new ArrayList<Double>();
		String sql = "SELECT m.price FROM menu m INNER JOIN orders o ON m.mid = o.mid";
		try (PreparedStatement getallpizzastatement = connection.prepareStatement(sql)) {
			ResultSet rs = getallpizzastatement.executeQuery();
			while (rs.next()) {
				Pizza pizzaCurrent = new Pizza();
				pizzaCurrent.setPrice(rs.getInt(1));
				priceList.add(pizzaCurrent.getPrice());
			}
			return priceList;
		}
	}

	@Override
	public void close() throws Exception {
		if (connection != null)
			connection.close();
	}

}

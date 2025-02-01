package com.dkte.pizzashop.tester;

import java.sql.SQLException;
import java.util.List;

import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Pizza;

public class test02 {

	public static void main(String[] args) {
		try {
			PizzaDao pizzadao = new PizzaDao();
			List<Pizza> l1 = pizzadao.getAllPizza();
			for (Pizza pizza : l1) {
				pizza.displayPizzaMenu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

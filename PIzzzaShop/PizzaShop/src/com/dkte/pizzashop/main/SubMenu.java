package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.OrderDao;
import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;

public class SubMenu {
	public static void PizzaMenu() {
		try (PizzaDao pizzaDao = new PizzaDao()) {
//			pizzaDao.getAllPizza().forEach(p->System.out.println(p));

			List<Pizza> l1 = pizzaDao.getAllPizza();
			for (Pizza pizza : l1) {
				pizza.displayPizzaMenu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static void OrderPizza(Scanner sc, Customer customer) {
		try (OrderDao orderdao = new OrderDao()) {
			System.out.println("Enter the Menu Id of pizza : ");
			int mid = sc.nextInt();
			orderdao.PlaceOrder(customer.getCid(), mid);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void OrderHistry(Scanner sc, Customer customer) {
		try (OrderDao orderdao = new OrderDao()) {
//			List<Pizza> l1 = orderdao.OrderHistry(customer.getCid());
//			for (Pizza pizza : l1) {
//				pizza.displayPizzaMenu();
//			}
			orderdao.OrderHistry(customer.getCid()).forEach(p->System.out.println(p));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private static int menu(Scanner sc) {
		System.out.println();
		System.out.println("***** WELCOME TO PIZZA STORE *******");
		System.out.println("0. Logout");
		System.out.println("1. Pizza menu");
		System.out.println("2. Order a Pizza");
		System.out.println("3. Order History");
		System.out.print("Enter the choice : ");
		int choice = sc.nextInt();
		System.out.println("************************************");
		return choice;
	}

	public static void main(Scanner sc, Customer customer) {

		int choice;
		while ((choice = menu(sc)) != 0) {
			switch (choice) {
			case 1:
				PizzaMenu();
				break;
			case 2:
				OrderPizza(sc, customer);
   				break;
			case 3:
				OrderHistry(sc,customer);
				break;

			default:
				break;
			}
		}
	}
}


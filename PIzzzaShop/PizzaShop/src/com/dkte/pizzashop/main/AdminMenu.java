package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.dao.OrderDao;
import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;

public class AdminMenu {

	public static boolean AddPizza(Scanner sc) {
		try {
			PizzaDao pizzadao = new PizzaDao();
			Pizza newPizza = new Pizza();

			System.out.print("Enter the name of the pizza: ");
			sc.nextLine();
			String name = sc.nextLine();
			newPizza.setName(name);

			System.out.print("Enter the description of the pizza: ");
			String description = sc.nextLine();
			newPizza.setDescription(description);

			System.out.print("Enter the price: ");
			int price = sc.nextInt();
			newPizza.setPrice(price);

			pizzadao.AddPizza(newPizza);

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean UpdatePrice(Scanner sc) {
		try {

			System.out.print("Enter the mid of pizza : ");
			int mid = sc.nextInt();
			System.out.println("Enter the new price of pizza :");
			double price = sc.nextDouble();
			PizzaDao pizzadao = new PizzaDao();
			pizzadao.UpdatePrice(mid, price);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	private static boolean DisplayAllCustomers() {
		try {
			CustomerDao customerDao = new CustomerDao();
//			customerDao.DisplayAllCustomers().forEach(p->System.out.println(p));

			List<Customer> l1 = customerDao.DisplayAllCustomers();
			for (Customer customer : l1) {
				System.out.println(customer.toString());
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	private static boolean DisplayAllOrders() {
		try {
			OrderDao orderDao = new OrderDao();
			List<Pizza> l1 = orderDao.getAllOrders();
			for (Pizza pizza : l1) {
				System.out.println(pizza.toString());
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	private static void CalculateTotalProfit() {
		try {
			OrderDao orderDao = new OrderDao();
			List<Double> l1 = orderDao.getTotalProfit();
			System.out.print("The total sell is : ");
			double total = 0;
			for (Double double1 : l1) {
				total += double1;
			}
			System.out.println(total);
			System.out.print("Total profit is : ");
			System.out.println(total * 0.2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static int menu(Scanner sc) {
		System.out.println("***** WELCOME ADMIN *****");
		System.out.println("0. Exit");
		System.out.println("1. Add new Pizza");
		System.out.println("2. Update price");
		System.out.println("3. Display All Customers");
		System.out.println("4. Display all orders");
		System.out.println("5. Calculate Total Profit");
		System.out.print("Enter the choice : ");
		int choice = sc.nextInt();
		System.out.println("************************************");
		return choice;
	}

	public static void main(Scanner sc) {
		int choice;
		while ((choice = menu(sc)) != 0) {
			switch (choice) {
			case 1:
				if (AddPizza(sc)) {
					System.out.println("Pizza added succesfully :)");
				} else {
					System.out.println("try again !!!");
				}
				break;
			case 2:
				if (UpdatePrice(sc)) {
					System.out.println("Price Updated succesfully :)");
				} else {
					System.out.println("try again !!!");
				}
				break;
			case 3:
				if (DisplayAllCustomers()) {
					System.out.println("All customers Displayed :)");
				} else {
					System.out.println("try again !!!");
				}
				break;
			case 4:
				if (DisplayAllOrders()) {
					System.out.println("All Orders Displayed :)");
				} else {
					System.out.println("try again !!!");
				}
				break;
			case 5:
				CalculateTotalProfit();
				break;

			}
		}

	}

}

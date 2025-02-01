package com.dkte.pizzashop.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.entities.Customer;

public class MainMenu {

	public static void register(Scanner sc) {
		try {
			Customer customer = new Customer();
			CustomerDao customerDao = new CustomerDao();
			customerDao.insertCustomer(customer, sc);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Customer loginUser(Scanner sc) {
		try {
			System.out.print("Enter the Email : ");
			String email = sc.next();
			System.out.print("Enter the Password : ");
			String password = sc.next();
			CustomerDao customerDao = new CustomerDao();
			return customerDao.loginCustomer(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static boolean AdminLogin(Scanner sc) {
		System.out.print("Enter the Email id : ");
		String email = sc.next();
		System.out.print("Enter the password : ");
		String password = sc.next();

		if (email.equals("admin@gmail.com")  && password.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

	private static int menu(Scanner sc) {
		System.out.println("***** WELCOME TO PIZZA STORE *******");
		System.out.println("0. Exit");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Admin");
		System.out.print("Enter the choice : ");
		int choice = sc.nextInt();
		System.out.println("************************************");
		return choice;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int choice;
		while ((choice = menu(sc)) != 0) {
			switch (choice) {
			case 1:
				Customer customer = loginUser(sc);
				if (customer != null) {
					SubMenu.main(sc, customer);
				} else {
					System.out.println("Invalid...");
				}
				break;

			case 2:
				register(sc);
				break;

			case 3:
				if (AdminLogin(sc)) {
					AdminMenu.main(sc);
				} else {
					System.out.println("Invalid Crendentials ... :(");
				}

				break;
			}
		}
	}
}

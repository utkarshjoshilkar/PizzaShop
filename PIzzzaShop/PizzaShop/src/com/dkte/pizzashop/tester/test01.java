package com.dkte.pizzashop.tester;

import java.sql.SQLException;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.entities.Customer;

public class test01 {

	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		
		try {
			CustomerDao cd = new CustomerDao();
			String email = sc.next();
			String password = sc.next();
			cd.loginCustomer(email,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



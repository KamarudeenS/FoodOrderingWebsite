package com.food.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/foodapplication";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "K@m@r";

	public static Connection getConnection()
	{
		 Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			  System.out.println("✅ DB connected successfully!");
		} catch (ClassNotFoundException e) {
			 System.out.println("❌ JDBC Driver not found");
			e.printStackTrace();
		} catch(SQLException se) {
			System.out.println("❌ Database connection failed");
			se.printStackTrace();
		}
		return connection;
	}

}

package com.tsystems.bookstore.persistence.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
	
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
//	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/bookstore";
//	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:tuni";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "admin";
	
	public static Connection getDBConnection() {
		Connection dbConnection = null;
		
		System.out.println("Set up driver");
		
		try {
			Class.forName(DB_DRIVER);
			System.out.println("JDBC Driver registred!");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is JDBC DRIVER?");
			System.out.println(e.getMessage());
		}
		
		try {
			dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connection established!\n");
			return dbConnection;
		} catch (SQLException e) {
			System.out.println("Connection faild!");
			System.out.println(e.getMessage());
		}
		
		return dbConnection;
	}
}

package com.demo.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

	private JdbcUtil() {

	}

	static {
		// Load and register Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Loading driver...");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getJdbConnection() throws SQLException, IOException {

		// Take data from properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\VERMA_JI_KA_LAUNDA\\eclipse-workspace\\JDBCStandardApp\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);

		// Establish Connection
		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("username"), properties.getProperty("password"));
		System.out.println("connection object created...");
		return connection;
	}

	public static void cleanUp(Connection conn, Statement statement, ResultSet resultSet) throws SQLException {

		// Close resource
		if (conn != null) {
			conn.close();
		}

		if (statement != null) {
			statement.close();
		}

		if (resultSet != null) {
			resultSet.close();
		}

		System.out.println("Closed resources...");
	}

}

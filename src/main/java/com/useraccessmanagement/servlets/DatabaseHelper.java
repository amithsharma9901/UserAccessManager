package com.useraccessmanagement.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
	
    // Hardcode the database connection information
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/access_mgmt";
    private static final String DB_USERNAME = "amith";
    private static final String DB_PASSWORD = "990189";
   
    // Method to get the connection
    public static Connection getConnection() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    // Method to check if the database connection is successful
    public static void checkConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}

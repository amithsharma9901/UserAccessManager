package com.useraccessmanagement.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "SELECT id, username, role FROM users"; // Adjust according to your user table
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String role = resultSet.getString("role");
                    users.add(new User(id, username, role)); // Ensure you have a User class with the appropriate constructor
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching users.");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsp/adminDashboard.jsp").forward(request, response);
    }

    // User class to hold user details
    public static class User {
        private int id;
        private String username;
        private String role;

        public User(int id, String username, String role) {
            this.id = id;
            this.username = username;
            this.role = role;
        }

        public int getId() { return id; }
        public String getUsername() { return username; }
        public String getRole() { return role; }
    }
}

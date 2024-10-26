

package com.useraccessmanagement.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@WebServlet("/viewUsers")
public class ViewUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!SessionHelper.isAdmin(request)) {
            response.sendRedirect("login");
            return;
        }

        List<User> users = new ArrayList<>();
        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "SELECT id, username, role FROM users"; // Adjust based on your schema
            try (PreparedStatement statement = conn.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String username = resultSet.getString("username");
                    String role = resultSet.getString("role");
                    users.add(new User(id, username, role));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching users.");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }

        request.setAttribute("users", users);
        request.getRequestDispatcher("/jsp/viewUsers.jsp").forward(request, response);
    }

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

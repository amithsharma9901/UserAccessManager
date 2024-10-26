package com.useraccessmanagement.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Servlet mapping
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to the sign-up page
        request.getRequestDispatcher("/jsp/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate input
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Username and password are required.");
            request.getRequestDispatcher("/jsp/signup.jsp").forward(request, response);
            return;
        }

        // Check if the username already exists
       

        // Store the new user in the database
        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, 'Employee')";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password); // Consider hashing the password before storing
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while signing up. Please try again.");
            request.getRequestDispatcher("/jsp/signup.jsp").forward(request, response);
            return;
        }

        // Redirect to the login page upon successful registration
        response.sendRedirect("login.jsp");
    }

   
}

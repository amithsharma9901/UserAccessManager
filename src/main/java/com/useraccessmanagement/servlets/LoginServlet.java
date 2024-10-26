package com.useraccessmanagement.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String role = resultSet.getString("role");
                int userId = resultSet.getInt("id"); // Assuming your users table has an id column

                // Store user data in the session
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", role);
                session.setAttribute("userId", userId); // Store userId in the session

                // Redirect based on the role
                if ("Admin".equalsIgnoreCase(role)) {
                    response.sendRedirect("adminDashboard");
                } else if ("Manager".equalsIgnoreCase(role)) {
                    response.sendRedirect("pendingRequests");
                } else {
                    response.sendRedirect("requestAccess");
                }
            } else {
                response.sendRedirect("login?error=true");  // Redirect with an error message
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }


        
    
}

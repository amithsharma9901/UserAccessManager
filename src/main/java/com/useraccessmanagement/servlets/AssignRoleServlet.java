
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

@WebServlet("/assignRole")
public class AssignRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!SessionHelper.isAdmin(request)) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = Integer.parseInt(request.getParameter("userId"));
        String newRole = request.getParameter("role");

        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "UPDATE users SET role = ? WHERE id = ?";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, newRole);
                statement.setInt(2, userId);
                statement.executeUpdate();
            }
            response.sendRedirect("viewUsers");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error assigning role: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }
}

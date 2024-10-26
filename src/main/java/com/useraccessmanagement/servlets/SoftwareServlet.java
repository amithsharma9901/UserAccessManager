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

@WebServlet("/createSoftware")
public class SoftwareServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/createSoftware.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String softwareName = request.getParameter("name");
        String description = request.getParameter("description");
        String accessLevels = request.getParameter("accessLevels");

        // Validate input
        if (softwareName == null || description == null || accessLevels == null ||
            softwareName.isEmpty() || description.isEmpty() || accessLevels.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/jsp/createSoftware.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, softwareName);
                statement.setString(2, description);
                statement.setString(3, accessLevels);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while creating the software. Please try again.");
            request.getRequestDispatcher("/jsp/createSoftware.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("dashboard");
    }
}

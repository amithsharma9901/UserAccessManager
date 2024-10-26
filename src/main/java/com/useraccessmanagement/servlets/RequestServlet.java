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
import java.sql.SQLException;

@WebServlet("/requestAccess")
public class RequestServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve parameters from the form
        String softwareId = request.getParameter("softwareId");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        // Retrieve user ID from the session
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId"); // Ensure this is set when the user logs in
        

        // Check if userId is null (not logged in)
        if (userId == null) {
            request.setAttribute("error", "User is not logged in.");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }

        // Status is typically set to "Pending" initially
        String status = "Pending";

        // Insert into the database
        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, userId); // Automatically set user ID from session
                statement.setInt(2, Integer.parseInt(softwareId)); // Parse softwareId to int
                statement.setString(3, accessType);
                statement.setString(4, reason);
                statement.setString(5, status);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("requested successfully"); // Redirect to a success page
                } else {
                    request.setAttribute("error", "Failed to request access.");
                    request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
            request.setAttribute("error", "An error occurred while processing your request: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirect to the form page or show an error message
        request.getRequestDispatcher("/jsp/requestAccess.jsp").forward(request, response);
    }
}

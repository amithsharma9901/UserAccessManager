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

@WebServlet("/pendingRequests")
public class PendingRequestsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Request> pendingRequests = new ArrayList<>();
        
        try (Connection conn = DatabaseHelper.getConnection()) {
            String sql = "SELECT id, user_id, software_id, access_type, reason, status FROM requests WHERE status = 'Pending'";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int userId = resultSet.getInt("user_id");
                    int softwareId = resultSet.getInt("software_id");
                    String accessType = resultSet.getString("access_type");
                    String reason = resultSet.getString("reason");
                    String status = resultSet.getString("status");

                    pendingRequests.add(new Request(id, userId, softwareId, accessType, reason, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while fetching pending requests.");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }
        
        request.setAttribute("pendingRequests", pendingRequests);
        request.getRequestDispatcher("/jsp/pendingRequests.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String requestIdStr = request.getParameter("requestId");
        
        if (requestIdStr == null || action == null) {
            request.setAttribute("error", "Invalid request.");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }

        try {
            int requestId = Integer.parseInt(requestIdStr);
            String newStatus = action.equals("approve") ? "Approved" : "Rejected";

            try (Connection conn = DatabaseHelper.getConnection()) {
                String sql = "UPDATE requests SET status = ? WHERE id = ?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setString(1, newStatus);
                    statement.setInt(2, requestId);

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        // Successfully updated, redirect to the same page to refresh pending requests
                        response.sendRedirect("pendingRequests");
                        System.out.println("action performed successfully");
                    } else {
                        request.setAttribute("error", "No request found with the given ID.");
                        request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
                    }
                }
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid request ID format.");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while updating the request: " + e.getMessage());
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    // Request class to hold request details
    public static class Request {
        private int id; // Add id here
        private int userId;
        private int softwareId;
        private String accessType;
        private String reason;
        private String status;

        public Request(int id, int userId, int softwareId, String accessType, String reason, String status) {
            this.id = id; // Set id
            this.userId = userId;
            this.softwareId = softwareId;
            this.accessType = accessType;
            this.reason = reason;
            this.status = status;
        }

        // Getters
        public int getId() { return id; } // Add getter for id
        public int getUserId() { return userId; }
        public int getSoftwareId() { return softwareId; }
        public String getAccessType() { return accessType; }
        public String getReason() { return reason; }
        public String getStatus() { return status; }
    }
}

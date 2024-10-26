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

@WebServlet("/approveRequest")
public class ApprovalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Assuming the requestId is passed in the URL
        String requestIdStr = request.getParameter("requestId");

        if (requestIdStr == null) {
            request.setAttribute("error", "No request ID provided.");
            request.getRequestDispatcher("/jsp/error.jsp").forward(request, response);
            return;
        }

        // Retrieve the request details from the database using requestId
        // (This part is not shown, but you would query the database to get the details)
        // Set the attributes for JSP
        request.setAttribute("requestId", requestIdStr);
        request.setAttribute("accessType", "Example Access Type"); // Replace with actual value from DB
        request.setAttribute("reason", "Example Reason"); // Replace with actual value from DB

        // Forward to the JSP page
        request.getRequestDispatcher("/jsp/approveRequest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status"); // Expecting "Approved" or "Rejected"
        String requestIdStr = request.getParameter("requestId"); // Request ID from the form

        try {
            int requestId = Integer.parseInt(requestIdStr); // Ensure this is an int

            try (Connection conn = DatabaseHelper.getConnection()) {
                String sql = "UPDATE requests SET status = ? WHERE id = ?";
                try (PreparedStatement statement = conn.prepareStatement(sql)) {
                    statement.setString(1, status);
                    statement.setInt(2, requestId); // Ensure this is an int

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        response.sendRedirect("success.jsp"); // Redirect to success page
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
}

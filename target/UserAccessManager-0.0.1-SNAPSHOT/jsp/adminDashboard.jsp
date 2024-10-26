<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS -->
    <title>Admin Dashboard</title>
</head>
<body>
    <div class="container">
        <h2>Admin Dashboard</h2>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>

        <nav>
            <ul>
                <li><a href="viewUsers">View Users</a></li>
                <li><a href="createSoftware">Create Software</a></li>
                <li><a href="viewPendingRequests">View Pending Requests</a></li>
                <li><a href="assignRoles">Assign Roles</a></li>
                <li><a href="logout">Logout</a></li>
            </ul>
        </nav>
    </div>
</body>
</html>

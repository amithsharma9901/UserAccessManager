<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    <style>
        /* General Styles */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        /* Container */
        .container {
            width: 100%;
            max-width: 600px;
            background: #ffffff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        /* Title */
        .container h2 {
            font-size: 24px;
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        /* Error Message */
        .error {
            color: #d9534f;
            background-color: #f8d7da;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            text-align: center;
        }

        /* Navigation Menu */
        nav ul {
            list-style-type: none;
            padding: 0;
            display: flex;
            justify-content: space-around;
            background-color: #007bff;
            border-radius: 8px;
            padding: 10px;
        }

        nav ul li {
            margin: 0 10px;
        }

        nav ul li a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
            transition: color 0.3s ease;
        }

        nav ul li a:hover {
            color: #ffd700;
        }
    </style>
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
                <li><a href="login">Logout</a></li>
            </ul>
        </nav>
    </div>
</body>
</html>

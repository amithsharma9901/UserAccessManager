<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
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
            max-width: 400px;
            background: #ffffff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        /* Title */
        .container h1 {
            font-size: 24px;
            color: #333;
            margin-bottom: 20px;
        }

        /* Form Elements */
        form label {
            font-weight: bold;
            display: block;
            margin: 10px 0 5px;
            color: #333;
        }

        form input[type="text"],
        form input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 5px 0 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        form button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        form button:hover {
            background-color: #0056b3;
        }

        /* Error Message */
        .error {
            color: #d9534f;
            margin-top: 10px;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        <form action="login" method="post">
            <label>Username:</label>
            <input type="text" name="username" required />
            <label>Password:</label>
            <input type="password" name="password" required />
            <button type="submit">Login</button>
        </form>

        <% if (request.getParameter("error") != null) { %>
            <p class="error">Invalid username or password</p>
        <% } %>
    </div>
</body>
</html>

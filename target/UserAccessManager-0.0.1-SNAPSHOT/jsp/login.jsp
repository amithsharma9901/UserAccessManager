<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h1>Login</h1>
    <form action="login" method="post">
        <label>Username: <input type="text" name="username" /></label><br>
        <label>Password: <input type="password" name="password" /></label><br>
        <button type="submit">Login</button>
    </form>

    <% if (request.getParameter("error") != null) { %>
        <p style="color:red;">Invalid username or password</p>
    <% } %>
</body>
</html>

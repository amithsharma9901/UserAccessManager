<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Request Access</title>
    <style>
        .form-container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f9f9f9;
            font-family: Arial, sans-serif;
        }
        .form-container h2 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
            color: #555;
        }
        select, textarea, button {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-size: 14px;
        }
        button {
            margin-top: 15px;
            background-color: #4CAF50;
            color: white;
            font-weight: bold;
            cursor: pointer;
            border: none;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2>Request Access</h2>
    <form action="requestAccess" method="post">
        <label for="softwareId">Software</label>
        <select name="softwareId" id="softwareId">
            <c:forEach var="software" items="${softwareList}">
                <option value="${software.id}">${software.name}</option>
            </c:forEach>
        </select>

        <label for="accessType">Access Type</label>
        <select name="accessType" id="accessType">
            <option value="Read">Read</option>
            <option value="Write">Write</option>
        </select>

        <label for="reason">Reason</label>
        <textarea name="reason" id="reason" rows="4" placeholder="Reason for request"></textarea>

        <button type="submit">Request Access</button>
    </form>
</div>
</body>
</html>

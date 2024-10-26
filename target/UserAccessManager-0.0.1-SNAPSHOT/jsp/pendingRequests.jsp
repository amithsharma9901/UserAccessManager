<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Pending Requests</title>
</head>
<body>
    <h2>Pending Access Requests</h2>

    <c:if test="${not empty error}">
        <div style="color:red;">${error}</div>
    </c:if>

    <table border="1">
        <thead>
            <tr>
                <th>Request ID</th>
                <th>User ID</th>
                <th>Software ID</th>
                <th>Access Type</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="request" items="${pendingRequests}">
                <tr>
                    <td>${request.id}</td>
                    <td>${request.userId}</td>
                    <td>${request.softwareId}</td>
                    <td>${request.accessType}</td>
                    <td>${request.reason}</td>
                    <td>${request.status}</td>
                    <td>
                        <form action="pendingRequests" method="post">
                            <input type="hidden" name="requestId" value="${request.id}"/>
                            <input type="hidden" name="action" value="approve"/>
                            <button type="submit">Approve</button>
                        </form>
                        <form action="pendingRequests" method="post">
                            <input type="hidden" name="requestId" value="${request.id}"/>
                            <input type="hidden" name="action" value="reject"/>
                            <button type="submit">Reject</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

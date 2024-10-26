<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Approve or Reject Request</title>
</head>
<body>
<h2>Request Approval</h2>
<% 
    Integer requestId = (Integer) request.getAttribute("requestId"); // Get request ID from request attributes
    String accessType = (String) request.getAttribute("accessType"); // Get access type
    String reason = (String) request.getAttribute("reason"); // Get reason

    if (requestId == null) {
        out.println("<p>Error: No request found.</p>");
    } else {
%>
    <p>Request ID: <%= requestId %></p>
    <p>Access Type: <%= accessType %></p>
    <p>Reason: <%= reason %></p>

    <form action="<%= request.getContextPath() %>/approveRequest" method="post">
        <input type="hidden" name="requestId" value="<%= requestId %>"/>
        <button type="submit" name="status" value="Approved">Approve</button>
        <button type="submit" name="status" value="Rejected">Reject</button>
    </form>
<% 
    } 
%>
</body>
</html>

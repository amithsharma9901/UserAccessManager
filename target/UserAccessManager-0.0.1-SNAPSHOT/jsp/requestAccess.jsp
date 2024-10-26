<form action="requestAccess" method="post">
    <select name="softwareId">
        <option value="1">Software 1</option>
        <option value="2">Software 2</option>
        <!-- Dynamically populate options from database -->
    </select>
    <select name="accessType">
        <option value="Read">Read</option>
        <option value="Write">Write</option>
        <option value="Admin">Admin</option>
    </select>
    <textarea name="reason" placeholder="Reason for request"></textarea>
    <button type="submit">Request Access</button>
</form>

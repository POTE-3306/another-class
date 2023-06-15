<%@ page import="simple.project.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
%>
<html>
<head>
    <title><%= user.getName() %></title>
</head>
<body>
    <div>
        <%= user.getId() %>
    </div>
    <div>
        <%= user.getEmail() %>
    </div>
    <div>
        <%= user.getName() %>
    </div>
    <div>
        <%= user.getAge() %>
    </div>
    <div>
        <%= user.getGender() %>
    </div>
    <div>
        <%= user.isAdmin() %>
    </div>
    <div>
        <%= user.getNaverId() %>
    </div>
</body>
</html>

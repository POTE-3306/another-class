<%@ page import="simple.project.user.User" %><%--
  Created by IntelliJ IDEA.
  User: msoo6
  Date: 2023-06-15
  Time: 오후 4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    User user = (User) request.getAttribute("user");
%>
<body>
    <h1>My Page</h1>
    <h1><%=user.getName()%></h1>
    <h1><%=user.getAge()%></h1>
    <h1><%=user.getGender()%></h1>
</body>
</html>

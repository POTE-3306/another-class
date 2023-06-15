<%--
  Created by IntelliJ IDEA.
  User: msoo6
  Date: 2023-06-15
  Time: 오후 4:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ClassPage</title>
</head>
<body>
<%
    String url = request.getRequestURI();
    String path = request.getContextPath();
%>
<h1>ClassPage</h1>
<h4><%=(String) request.getAttribute("classId")%></h4>
<a href="#">mypage</a>
<a href="#">게시글리스트</a>
<a href="#">강의페이지</a>
<a href="#"></a>
</body>
</html>

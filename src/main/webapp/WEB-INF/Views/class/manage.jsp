<%@ page import="simple.project.user.User" %>
<%@ page import="simple.project.course.Course" %><%--
  Created by IntelliJ IDEA.
  User: msoo6
  Date: 2023-06-16
  Time: 오전 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%
    User user = (User) request.getAttribute("user");
    Course course = (Course) request.getAttribute("course");
%>
<body>
<div>
    <h1>ClassPage</h1>
    <h4><%=(String) request.getAttribute("classId")%>
    </h4>
    <%--    TODO: myPage 추가--%>
    <a href="/another-class/mypage">마이페이지</a>

    <% if (user.isAdmin()) {%>
    <a href=<%=String.format("%d/manage", course.getId())%>>관리</a>
    <%}%>

    <a href=<%=String.format("%d/community", course.getId())%>>커뮤니티</a>
    <a href="/another-class/post/main">강의 목록 가기</a>
</div>
<div>
    <a
</div>

</body>
</html>

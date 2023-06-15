<%@ page import="simple.project.course.Course" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: msoo6
  Date: 2023-06-15
  Time: 오후 4:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Course> courseList = (List<Course>) request.getAttribute("courseList");
    %>
%>
<h1>classList</h1>
<div>
    <ul>
        <%
            for (Course course :
                    courseList) {%>
            <li><%=course.getName()%></li>
            <li><a href=<%=String.format("class/%s", course.getId())%>>접속링크</a></li>
            <%}
        %>
    </ul>

</div>

</body>
</html>

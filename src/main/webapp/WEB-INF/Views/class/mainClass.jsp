<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="simple.project.user.User" %>
<%@ page import="simple.project.courseplan.CoursePlan" %>
<%@ page import="simple.project.course.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ClassPage</title>
</head>
<body>
<%
    String url = request.getRequestURI();
    String path = request.getContextPath();
//    유저, 수업아이디, 수업, 수업 계획
    String classId = (String) request.getAttribute("classId");
    User user = (User) request.getAttribute("user");
    Course course = (Course) request.getAttribute("course");
    CoursePlan coursePlan = (CoursePlan) request.getAttribute("coursePlan");

    List<HashMap<Integer, ArrayList<String>>> postInfoList = (List<HashMap<Integer, ArrayList<String>>>) request.getAttribute("postInfoList");
%>
<h1>ClassPage</h1>
<h4><%=(String) request.getAttribute("classId")%></h4>
<a href="#">mypage</a>
<a href="#">게시글리스트</a>
<a href="#">강의페이지</a>
<a href="#"></a>

<!-- Display post information -->
<h2>Post List:</h2>
<table>
    <thead>
    <tr>
        <th>Post ID</th>
        <th>Title</th>
        <th>Comment Count</th>
    </tr>
    </thead>
    <tbody>
    <% for (HashMap<Integer, ArrayList<String>> postMap : postInfoList) { %>
    <% for (Map.Entry<Integer, ArrayList<String>> entry : postMap.entrySet()) { %>
    <tr>
        <td><%= entry.getKey() %></td>
        <% for (String value : entry.getValue()) { %>
        <td><%= value %></td>
        <% } %>
    </tr>
    <% } %>
    <% } %>
    </tbody>
</table>
</body>
</html>

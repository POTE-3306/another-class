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
<%--    TODO: myPage 추가--%>
<a href="/another-class/mypage">마이페이지</a>
<a href=<%=String.format("/another-class/lecture/%d/attend", course.getId())%>> 출결 </a>

<% if(user.isAdmin()){%>
    <a href=<%=String.format("/another-class/lecture/%d/manage", course.getId())%>>관리</a>
<%}%>

<a href=<%=String.format("/another-class/lecture/%d/community", course.getId())%>>커뮤니티</a>
<a href="/another-class/post/main">강의 목록 가기</a>

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

<%@ page import="simple.project.user.User" %>
<%@ page import="simple.project.course.Course" %>
<%@ page import="simple.project.registration.Registration" %>
<%@ page import="simple.project.lecture.RegisterWaiting" %>
<%@ page import="java.util.List" %><%--
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
    List<RegisterWaiting> waitingList = (List<RegisterWaiting>) request.getAttribute("waitingList");
%>
<body>
<div>
    <h1>ClassPage</h1>
    <h4><%=(String) request.getAttribute("classId")%>
    </h4>
    <%--    TODO: myPage 추가--%>
    <a href="/another-class/mypage">마이페이지</a>

    <% if (user.isAdmin()) {%>
    <a href="<%=String.format("%d/manage", course.getId())%>">관리</a>
    <%}%>

    <a href="<%=String.format("%d/community", course.getId())%>">커뮤니티</a>
    <a href="/another-class/post/main">강의 목록 가기</a>
</div>
<div>
    <h1>강의정보 : <%=course.getUuid()%></h1>
</div>
<hr/>
<div>
    <h3>가입승인</h3>
    <br/>
    <table>
        <thead>
        <tr>
            <th>RegId</th>
            <th>학생ID</th>
            <th>학생이름</th>
            <th>수락</th>
            <th>거절</th>
        </tr>
        </thead>
        <tbody>
        <% for (RegisterWaiting waiting : waitingList) { %>
        <tr>
            <td><%= waiting.getRegId() %></td>
            <td><%= waiting.getUserId() %></td>
            <td><%= waiting.getUserName() %></td>
            <td><a href="<%= String.format("/another-class/lecture/%d/accept?regId=%d",course.getId(), waiting.getRegId()) %>">수락</a></td>
            <td><a href="<%= String.format("/another-class/lecture/%d/reject?regId=%d", course.getId(),waiting.getRegId()) %>">거절</a></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<hr/>
<div>
    <h3>출결관리</h3>
</div>

</body>
</html>

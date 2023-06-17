<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="simple.project.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="simple.project.course.Course" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%--
  Created by IntelliJ IDEA.
  User: msoo6
  Date: 2023-06-15
  Time: 오후 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HashMap<Integer, ArrayList<String>> map
            = (HashMap<Integer, ArrayList<String>>) request.getAttribute("attendMap");
%>
<html>
<head>
    <title>ClassPage</title>
</head>
<body>
<%
    User user = (User) request.getAttribute("user");
    List<Integer> isAttendUserIds = (List<Integer>) request.getAttribute("isAttendUserIds");
//  List<User> notAttendUserList = (List<User>) request.getAttribute("notAttendUserList");
    Course course = (Course) request.getAttribute("course");
//  String attendType = (String) request.getAttribute("type");
//  Integer attendType = (Integer) request.getAttribute("attendType");
%>
<h1>출석페이지</h1>
<a href="/another-class/mypage">마이페이지</a>
<a href=<%=String.format("/another-class/lecture/%d/attend", course.getId())%>> 출결 </a>

<% if (user.isAdmin()) {%>
<a href=<%=String.format("/another-class/lecture/%d/manage", course.getId())%>>관리</a>
<%}%>

<a href=<%=String.format("/another-class/lecture/%d/community", course.getId())%>>커뮤니티</a>
<a href="/another-class/post/main">강의 목록 가기</a>

<%
    LocalDateTime today = LocalDateTime.now();
    LocalDateTime dateTimeToCheck = course.getLimit_time();
    boolean isToday = today.getYear() == dateTimeToCheck.getYear()
            && today.getMonth() == dateTimeToCheck.getMonth()
            && today.getDayOfMonth() == dateTimeToCheck.getDayOfMonth();
    if (user.isAdmin()) {
        System.out.println(course.getCode());
        if (course.getCode() == 0 || !isToday) {
%>
<div>
    <form method="post" action="attend/createAttend">
        <label for="name"> 출결 코드 생성 : </label>
        <input type="text" id="name" name="code" required><br><br>
        <input type="submit" value="제출">
    </form>
</div>
<% } else {%>
<div>
    <h1>존재하는 코드 :<%=course.getCode()%>
    </h1>
    <h1>만료 시점 : <%=course.getLimit_time().format(DateTimeFormatter.ofPattern("HH:mm"))%>
    </h1>
</div>
<% } %>
<% } else {
    if (course.getLimit_time().isBefore(LocalDateTime.now())) {%>
<div>
    <h1><%=course.getLimit_time().format(DateTimeFormatter.ofPattern("HH:mm"))%> 만료 되었습니다.</h1>
    <h1>선생님께 문의하세요.</h1>
</div>
<% } else {
    if (isAttendUserIds.contains(user.getId())) {
%>
<div>
  <h1>출결 완료</h1>
</div>
<% } else {
%>
<div>
    <form method="post" action="attend/createAttend">
        <label for="name1"> 출결 코드 : </label>
        <input type="text" id="name1" name="code" required><br><br>
        <input type="submit" value="제출">
    </form>
</div>
<% }
}
} %>

<%--<%--%>
<%--  ArrayList<String> list = new ArrayList<>();--%>
<%--  String userName = "";--%>
<%--  String courseName = "";--%>
<%--  int countAtend = 0;--%>
<%--  for (int userId : map.keySet()) {--%>
<%--    list = map.get(userId);--%>
<%--    userName = list.get(0);--%>
<%--    courseName = list.get(1);--%>
<%--    countAtend = Integer.parseInt(list.get(2));--%>
<%--  }--%>
<%--%>--%>

</body>
</html>

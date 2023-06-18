<%@ page import="simple.project.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="simple.project.course.Course" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User) request.getAttribute("user");
    Course course = (Course) request.getAttribute("course");
    List<Integer> userIds = (List<Integer>) request.getAttribute("attendList");
    int classId = (int) request.getAttribute("classId");
    LocalDateTime today = LocalDateTime.now();
    LocalDateTime dateTimeToCheck = course.getLimit_time();
    System.out.println(today + " : " + dateTimeToCheck);
    boolean isToday = today.getYear() == dateTimeToCheck.getYear()
            && today.getMonth() == dateTimeToCheck.getMonth()
            && today.getDayOfMonth() == dateTimeToCheck.getDayOfMonth();
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
%>
<html>
<head>
    <title>CLASS</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="/another-class/class/assets/css/main.css"/>
</head>
<body class="is-preload">
<div id="wrapper">
    <div id="main">
        <div class="inner">

            <header id="header">
                <a href='<%= "/another-class/lecture/" + classId %>' class="logo"><strong>CLASS</strong></a>
            </header>

            <section>
                <div class="row gtr-200">
                    <div class="col-4 col-12-medium">
                    </div>
                    <div class="col-4 col-12-medium">
                        <%
                            if (user.isAdmin()) {
                                if (course.getCode() == 0 || !isToday) {
                        %>
                        <form action="attend/createAttend" method="post" accept-charset="UTF-8">
                            <div class="row gtr-uniform">
                                <div class="col-12">
                                    <label for="name"> 출결 코드 생성 : </label>
                                    <input type="text" id="name" name="code" required>
                                </div>
                                <div class="col-12">
                                    <ul class="actions">
                                        <li><input type="submit" value="제출" class="primary"/></li>
                                    </ul>
                                </div>
                            </div>
                        </form>
                        <% } else if (currentTime.after(Timestamp.valueOf(course.getLimit_time())))  {%>
                        <form action="attend/createAttend" method="post" accept-charset="UTF-8">
                            <div class="row gtr-uniform">
                                <div class="col-12">
                                    <label for="name"> 출결 코드 생성 : </label>
                                    <input type="text" id="name" name="code" required>
                                </div>
                                <div class="col-12">
                                    <ul class="actions">
                                        <li><input type="submit" value="제출" class="primary"/></li>
                                    </ul>
                                </div>
                            </div>
                        </form>
                        <% } else {%>
                        <div class="box">
                            <p>존재하는 코드 :<%=course.getCode()%><br/>
                            </p>
                            <p>만료 시점 : <%=course.getLimit_time().format(DateTimeFormatter.ofPattern("HH:mm"))%>
                            </p>
                        </div>
                        <% } %>
                        <% } else {
                            if (course.getLimit_time().isBefore(LocalDateTime.now())) {
                        %>
                        <div class="box">
                            <p><%=course.getLimit_time().format(DateTimeFormatter.ofPattern("HH:mm"))%> 만료 되었거나, 아직 오픈되지
                                않았습니다.</p>
                            <p>선생님께 문의하세요.</p>
                        </div>
                        <% } else {
                            if (userIds.contains(user.getId())) {
                        %>
                        <div class="box">
                            <p>출결 완료</p>
                        </div>
                        <% } else {
                        %>
                        <form action="attend/createAttend" method="post" accept-charset="UTF-8">
                            <div class="row gtr-uniform">
                                <div class="col-12">
                                    <label for="name1"> 출결 코드 : </label>
                                    <input type="text" id="name1" name="code" required>
                                </div>
                                <div class="col-12">
                                    <ul class="actions">
                                        <li><input type="submit" value="제출" class="primary"/></li>
                                    </ul>
                                </div>
                            </div>
                        </form>
                        <% }
                        }
                        } %>
                    </div>
                    <div class="col-4 col-12-medium">
                    </div>
                </div>
            </section>
        </div>
    </div>

    <div id="sidebar">
        <div class="inner">
            <nav id="menu">
                <header class="major">
                    <h2>Menu</h2>
                </header>
                <ul>
                    <li><a href='<%= "/another-class/lecture/" + classId + "/attend" %>'>출석</a></li>
                    <li><a href='<%= "/another-class/lecture/" + classId + "/plan" %>'>강의 계획</a></li>
                    <li>
                        <span class="opener">커뮤니티</span>
                        <ul>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/community/material" %>'>수업자료</a>
                            </li>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/community/notice" %>'>공지방</a></li>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/community/talk" %>'>수다방</a></li>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/community/task" %>'>과제방</a></li>
                        </ul>
                    </li>
                    <li><a href="/another-class/post/main">강의 목록</a></li>
                    <li><a href="/another-class/mypage">마이페이지</a></li>
                    <% if (user.isAdmin()) {%>
                    <li>
                        <span class="opener">관리</span>
                        <ul>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/manage" %>'>일반관리</a></li>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/registerManage" %>'>등록관리</a></li>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/attendManage" %>'>출석관리</a></li>
                        </ul>
                    </li>

                    <%}%>
                </ul>
            </nav>
        </div>
    </div>
</div>
<script src="/another-class/class/assets/js/jquery.min.js"></script>
<script src="/another-class/class/assets/js/browser.min.js"></script>
<script src="/another-class/class/assets/js/breakpoints.min.js"></script>
<script src="/another-class/class/assets/js/util.js"></script>
<script src="/another-class/class/assets/js/main.js"></script>
</body>
</html>

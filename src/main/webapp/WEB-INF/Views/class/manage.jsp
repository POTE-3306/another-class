<%@ page import="simple.project.user.User" %>
<%@ page import="simple.project.course.Course" %>
<%@ page import="simple.project.registration.Registration" %>
<%@ page import="simple.project.lecture.RegisterWaiting" %>
<%@ page import="java.util.List" %>
<%@ page import="simple.project.lecture.AtendUserDto" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User) request.getAttribute("user");
    Course course = (Course) request.getAttribute("course");
    List<RegisterWaiting> waitingList = (List<RegisterWaiting>) request.getAttribute("waitingList");
    List<AtendUserDto> attendList = (List<AtendUserDto>) request.getAttribute("attendUserDtos");
    HashMap<Integer, LocalDateTime> todayAttend = (HashMap<Integer, LocalDateTime>) request.getAttribute("todayAttend");
    int classId = course.getId();
%>

<html>
<head>
    <title>Manage</title>
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
                <header clas="main">
                    <h1> 강의명 : <%= course.getName()%>
                    </h1>
                    <h3>강의코드: <%= course.getUuid() %>
                    </h3>
                </header>
                <hr/>
                <div>
                    <h1>가입승인</h1>
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
                            <td><%= waiting.getRegId() %>
                            </td>
                            <td><%= waiting.getUserId() %>
                            </td>
                            <td><%= waiting.getUserName() %>
                            </td>
                            <td>
                                <a href="<%= String.format("/another-class/lecture/%d/accept?regId=%d", classId, waiting.getRegId()) %>">수락</a>
                            </td>
                            <td>
                                <a href="<%= String.format("/another-class/lecture/%d/reject?regId=%d", classId, waiting.getRegId()) %>">거절</a>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>

                <hr/>
                <div>
                    <h1>출결관리</h1>
                    <h3>수강 중인 학생</h3>
                    <table>
                        <thead>
                        <tr>
                            <th>RegId</th>
                            <th>수강과목ID</th>
                            <th>학생ID</th>
                            <th>학생이름</th>
                            <th>금일출석여부</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for (AtendUserDto student : attendList) { %>
                        <tr>
                            <td><%= student.getId() %>
                            </td>
                            <td><%= student.getCourseId() %>
                            </td>
                            <td><%= student.getUserId() %>
                            </td>
                            <td><%= student.getName() %>
                            </td>
                            <td><%= (todayAttend.get(student.getUserId()) == null) ? "X" : todayAttend.get(student.getUserId()) %>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
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
                    <% if (user.isAdmin()) { %>
                    <li><a href='<%= "/another-class/lecture/" + classId + "/manage" %>'>관리</a></li>
                    <% } %>
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

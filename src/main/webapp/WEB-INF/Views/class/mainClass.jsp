<%@ page import="simple.project.user.User" %>
<%@ page import="simple.project.course.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String classId = (String) request.getAttribute("classId");
    User user = (User) request.getAttribute("user");
    Course course = (Course) request.getAttribute("course");
%>

<html>
<head>
    <title>CLASS</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="/another-class/class/assets/css/main.css" />
</head>
<body class="is-preload">
    <div id="wrapper">
        <div id="main">
            <div class="inner">

                <header id="header">
                    <a href='<%= "/another-class/lecture/" + classId %>' class="logo"><strong>CLASS</strong></a>
                </header>

                <section id="banner">
                    <div class="content">
                        <header>
                            <h1><%=course.getName()%><br/>
                            </h1>
                            <p><%=course.getDescription()%></p>
                        </header>
                        <ul class="actions">
                            <li><a href='<%= "/another-class/lecture/" + classId + "/plan" %>' class="button big">강의 계획</a></li>
                        </ul>
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
                                <li><a href='<%= "/another-class/lecture/" + classId + "/community/material" %>'>수업자료</a></li>
                                <li><a href='<%= "/another-class/lecture/" + classId + "/community/notice" %>'>공지방</a></li>
                                <li><a href='<%= "/another-class/lecture/" + classId + "/community/talk" %>'>수다방</a></li>
                                <li><a href='<%= "/another-class/lecture/" + classId + "/community/task" %>'>과제방</a></li>
                            </ul>
                        </li>
                        <li><a href="/another-class/post/main">강의 목록</a></li>
                        <% if(user.isAdmin()){%>
                        <li><a href='<%= "/another-class/lecture/" + classId + "/manage" %>'>관리</a></li>
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

<%@ page import="simple.project.user.User" %>
<%@ page import="simple.project.courseplan.CoursePlan" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int classId = (int) request.getAttribute("classId");
    User user = (User) request.getAttribute("user");
    List<CoursePlan> coursePlanList = (List<CoursePlan>) request.getAttribute("coursePlanList");
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

                <section>
                    <header class="main">
                        <h1>강의 계획</h1>
                    </header>
                    <%
                        for(CoursePlan coursePlan : coursePlanList) {
                    %>
                        <hr class="major" />
                        <h2><%= coursePlan.getTitle() %></h2>
                        <p><%= coursePlan.getDescription().replaceAll("\n", "<br/>") %></p>
                    <%
                        }
                        if(user.isAdmin()){
                    %>
                    <ul class="actions">
                        <li><a href='<%= "/another-class/course/makeCoursePlan/" + classId %>' class="button primary">강의 계획 추가</a></li>
                    </ul>
                    <%
                        }
                    %>
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
                        <li><a href="/another-class/mypage" >마이페이지</a></li>
                        <% if(user.isAdmin()){%>
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

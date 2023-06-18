<%@ page import="simple.project.user.User" %>
<%@ page import="simple.project.lecture.RegisterWaiting" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User) request.getAttribute("user");
    int classId = (int) request.getAttribute("classId");
    List<RegisterWaiting> waitingList = (List<RegisterWaiting>) request.getAttribute("waitingList");
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
                <div>
                    <h1>가입승인</h1>
                    <br/>
                    <div class="table-wrapper">
                        <table class="alt">
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
                    <li><a href="/another-class/mypage" >마이페이지</a></li>
                    <% if (user.isAdmin()) { %>
                    <li>
                        <span class="opener">관리</span>
                        <ul>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/manage" %>'>일반관리</a></li>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/registerManage" %>'>등록관리</a></li>
                            <li><a href='<%= "/another-class/lecture/" + classId + "/attendManage" %>'>출석관리</a></li>
                        </ul>
                    </li>
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

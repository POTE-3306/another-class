<%@ page import="simple.project.post.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="simple.project.user.User" %><%--
  Created by IntelliJ IDEA.
  User: msoo6
  Date: 2023-06-15
  Time: 오후 7:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Post> postList = (List<Post>) request.getAttribute("postList");
    String classId = (String) request.getAttribute("classId");
    Integer pageType = (Integer) request.getAttribute("pageType");
    User user = (User) request.getAttribute("user");
%>

<html>
<head>
    <title>Community</title>
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
                        <% if (user.isAdmin()) {%>
                            <% if (pageType == 1) {%>
                            <ul class="actions">
                                <li><a href="<%= String.format("create-post?pageType=%d", pageType)%>" class="button primary">공지 생성</a></li>
                            </ul>
                            <% } else if (pageType == 2) { %>
                            <ul class="actions">
                                <li><a href="<%= String.format("create-post?pageType=%d", pageType)%>" class="button primary">과제 생성</a></li>
                            </ul>
                            <% } else if (pageType == 3) { %>
                            <ul class="actions">
                                <li><a href="<%= String.format("create-post?pageType=%d", pageType)%>" class="button primary">수업 자료 생성</a></li>
                            </ul>
                            <% } %>
                        <%
                            }
                            if (pageType == 4) {
                        %>
                        <ul class="actions">
                            <li><a href="<%= String.format("create-post?pageType=%d", pageType)%>" class="button primary">수다방 방 생성</a></li>
                        </ul>
                        <%
                            } %>
                    </div>
                    <div class="table-wrapper">
                        <table class="alt">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>제목</th>
                                <th>내용</th>
                            </tr>
                            </thead>
                            <tbody>
                            <% for (Post post : postList) { %>
                            <tr onclick="goToPostDetail('<%= post.getId() %>', '<%= post.getBoardType() %>')">
                                <td><%= post.getId() %></td>
                                <td><%= post.getTitle()%></td>
                                <%
                                    String content = post.getContent();
                                    String mainContent = new String();
                                    if (content.length()>50)
                                        mainContent = content.substring(0,50) + "...";
                                    else
                                        mainContent = content;
                                %>
                                <td><%=mainContent%></td>
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
                        <li><a href="/another-class/mypage">마이페이지</a></li>
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
                        <% if (user.isAdmin()) {%>
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
<script>
    function goToPostDetail(postId, boardType) {
        window.location.href = "/another-class/post/" + postId + "?boardType=" + boardType;
    }
</script>
</html>

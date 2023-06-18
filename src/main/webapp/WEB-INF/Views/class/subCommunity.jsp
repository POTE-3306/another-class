<%@ page import="simple.project.post.PostDto" %>
<%@ page import="simple.project.user.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PostDto postDto = (PostDto) request.getAttribute("postDto");
    User user = (User) request.getAttribute("user");
    String boardType = (String) request.getAttribute("boardType");
    int classId = (int) request.getAttribute("classId");
%>
<html>
<head>
    <title>CLASS</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="/another-class/class/assets/css/main.css"/>
    <script>
        function goBack() {
            window.history.back(); // 뒤로가기 기능 실행
        }
    </script>
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
                            <h1><%= postDto.getTitle() %><br/>
                            </h1>
                            <span class="post-author">Author: <%= postDto.getAuthor() %></span>
                            <span class="post-time">Post Time: <%= postDto.getPostTime() %></span>
                        </header>
                        <br/>
                        <br/>
                        <div class="box">
                            <h2><%= postDto.getContent().replaceAll("\n", "<br/>") %></h2>
                        </div>
                    </div>
                    <button onclick="goBack()">뒤로가기</button>
                </section>

                <section>
                    <h3>Comments</h3>
                    <form method="post" action="/another-class/comment/createComment">
                        <label for="content">댓글:</label>
                        <textarea id="content" name="content" required></textarea>
                        <input type="hidden" name="user_id" value="<%= user.getId() %>">
                        <input type="hidden" name="post_id" value="<%= postDto.getPostId() %>">
                        <input type="hidden" name="boardType" value="<%= boardType %>">
                        <br/>
                        <button type="submit">제출</button>
                    </form>
                    <div class="comments">
                        <c:forEach var="commentDto" items="${commentDto}">
                            <div class="comment">
                                <div class="comment-author">${commentDto.author}</div>
                                <div class="comment-content">${commentDto.content}</div>
                                <div class="comment-time">${commentDto.postTime}</div>
                            </div>
                            <hr/>
                        </c:forEach>
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
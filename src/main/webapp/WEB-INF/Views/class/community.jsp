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
                <% if (user.isAdmin()) {%>
                <%--            게시판 네비게이션--%>
                <div>
                    <ul>
                        <% if (pageType == 1) {%>
                        <li>
                            <a href=<%= String.format("create-post?pageType=%d", pageType)%>>
                                <button type="button">공지 생성</button>
                            </a>
                        </li>
                        <% } else if (pageType == 2) { %>
                        <li>
                            <a href=<%= String.format("create-post?pageType=%d", pageType)%>>
                                <button type="button">과제 생성</button>
                            </a>
                        </li>
                        <% } else if (pageType == 3) { %>
                        <li>
                            <a href=<%= String.format("create-post?pageType=%d", pageType)%>>
                                <button type="button">수업 자료 생성</button>
                            </a>
                        </li>
                        <% } %>
                    </ul>
                    <%
                        }
                        if (pageType == 4) {
                    %>
                    <ul>
                        <li>
                            <a href=<%= String.format("create-post?pageType=%d", pageType)%>>
                                <button type="button">수다방 방 생성</button>
                            </a>
                        </li>
                    </ul>
                    <%
                        } %>
                </div>
                <div>
                    <%--            게시글 리스트--%>
                    <div>
                        <ul>
                            <% for (Post post : postList) { %>
                            <li style=" border: 2px solid rgba(61,49,49,0.95);
                                        border-radius: 10px;
                                        padding: 15px;
                                        box-shadow: 0px 0px 10px #999;
                                        background-color: rgba(255,255,255,0.83);">
                                <a href="#">
                                    <div>
                                        <a href=<%=String.format("/another-class/post/%d?boardType=%s", post.getId(),post.getBoardType())%>>
                                            <h3><%=post.getTitle()%>
                                            </h3>
                                                <%String content = post.getContent();
                    String mainContent = new String();
                    if (content.length()>50){
                        mainContent = content.substring(0,50) + "...";
                    }else mainContent=content;%>
                                            <p><%=mainContent%>
                                            </p>
                                    </div>
                                </a>
                            </li>
                            <% } %>
                            <br/>
                        </ul>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <%--                사이드바--%>
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
                    <% if (user.isAdmin()) {%>
                    <li><a href='<%= "/another-class/lecture/" + classId + "/manage" %>'>관리</a></li>
                    <li><a href="/another-class/mypage">마이페이지</a></li>
                    <%}%>
                </ul>
            </nav>
        </div>
    </div>
    <script src="/another-class/class/assets/js/jquery.min.js"></script>
    <script src="/another-class/class/assets/js/browser.min.js"></script>
    <script src="/another-class/class/assets/js/breakpoints.min.js"></script>
    <script src="/another-class/class/assets/js/util.js"></script>
    <script src="/another-class/class/assets/js/main.js"></script>
</body>
</html>
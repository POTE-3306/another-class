<%@ page import="simple.project.post.PostDto" %>
<%@ page import="simple.project.user.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PostDto postDto = (PostDto) request.getAttribute("postDto");
    User user = (User) request.getAttribute("user");
    String boardType = (String) request.getAttribute("boardType");
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
    <title>Community</title>
</head>
<body class="is-preload">
<div id="wrapper">
    <div id="main">
        <div class="inner">

            <div class="post">
                <div class="post-header">
                    <h1><%= postDto.getTitle() %></h1>
                    <div class="post-info">
                        <span class="post-author">Author: <%= postDto.getAuthor() %></span>
                        <span class="post-time">Post Time: <%= postDto.getPostTime() %></span>
                    </div>
                </div>
                <hr/>

                <div class="post-content">
                    <h2><%= postDto.getContent().replaceAll("\n", "<br/>") %></h2>
                </div>
            </h3>
            <br/>
            <button onclick="goBack()">뒤로가기</button>

            <hr/>
            <br/>
            <div class="post">
                <h3>Comments</h3>
                <form method="post" action="/another-class/comment/createComment">
                    <label for="content">댓글:</label>
                    <textarea id="content" name="content" required></textarea>
                    <input type="hidden" name="user_id" value="<%= user.getId() %>">
                    <input type="hidden" name="post_id" value="<%= postDto.getPostId() %>">
                    <input type="hidden" name="boardType" value="<%= boardType %>">
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
            </div>


            <br/>
        </div>
    </div>
</div>

</body>
</html>
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
    <script>
        function goBack() {
            window.history.back(); // 뒤로가기 기능 실행
        }
    </script>
    <title>Community</title>
</head>
<body>
<h1>Community</h1>
<button onclick="goBack()">뒤로가기</button>
<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Post Time</th>
        <th>Content</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%= postDto.getTitle()%>
        </td>
        <td><%= postDto.getAuthor()%>
        </td>
        <td><%= postDto.getContent()%>
        </td>
        <td><%= postDto.getPostTime()%>
        </td>
    </tr>
    </tbody>
</table>

<table>
    <thead>
    <tr>
        <th>Author</th>
        <th>Content</th>
        <th>Post Time</th>
    </tr>
    </thead>
    <tbody>
    <form method="post" action="/another-class/comment/createComment">
        <label for="content">댓글:</label>
        <textarea id="content" name="content" required></textarea>
        <input type="hidden" name="user_id" value="<%=user.getId()%>">
        <input type="hidden" name="post_id" value="<%=postDto.getPostId()%>">
        <input type="hidden" name="boardType" value="<%=boardType%>">
        <button type="submit">제출</button>
    </form>
    </tbody>
    <tbody>
    <c:forEach var="commentDto" items="${commentDto}">
        <tr>
            <td>${commentDto.author}</td>
            <td>${commentDto.content}</td>
            <td>${commentDto.postTime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<br/>
</body>
</html>
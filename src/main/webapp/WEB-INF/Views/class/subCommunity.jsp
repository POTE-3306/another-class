<%@ page import="simple.project.post.PostDto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    PostDto postDto = (PostDto) request.getAttribute("postDto");
%>
<html>
<head>
    <title>Community</title>
</head>
<body>
<h1>Community</h1>

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
            <td><%= postDto.getTitle()%></td>
            <td><%= postDto.getAuthor()%></td>
            <td><%= postDto.getContent()%></td>
            <td><%= postDto.getPostTime()%></td>
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

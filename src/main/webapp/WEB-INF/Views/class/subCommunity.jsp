<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <c:forEach var="postDto" items="${postDtos}">
        <tr>
            <td>${postDto.title}</td>
            <td>${postDto.author}</td>
            <td>${postDto.postTime}</td>
            <td>${postDto.content}</td>
        </tr>
    </c:forEach>
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

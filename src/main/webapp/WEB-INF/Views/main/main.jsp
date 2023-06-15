<%@ page import="simple.project.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="simple.project.post.Post" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
    List<Post> postList = (List<Post>) request.getAttribute("postList");
%>
<html>
<head>
    <title><%= user.getName() %></title>
</head>
<body>
<%-- TODO: 수업 코드 제출 주소--%>
<input>
<form action="" method="get">
    <label for="input-text">텍스트 입력:</label>
    <input type="text" id="input-text" name="classCode" placeholder="클래스코드입력">
    <input type="submit" value="검색">
</form>
<%-- TODO: 수업 생성 주소--%>
<a href="링크 주소">
    <button>버튼 텍스트</button>
</a>

    <% for (Post post: postList) { %>
<ul>
    <li><%= post.getTitle() %></li>
    <li><%= post.getContent() %></li>
</ul>
    <% } %>

<div>

        <%= user.getId() %>
    </div>
    <div>
        <%= user.getEmail() %>
    </div>
    <div>
        <%= user.getName() %>
    </div>
    <div>
        <%= user.getAge() %>
    </div>
    <div>
        <%= user.getGender() %>
    </div>
    <div>
        <%= user.isAdmin() %>
    </div>
    <div>
        <%= user.getNaverId() %>
    </div>
</body>
</html>

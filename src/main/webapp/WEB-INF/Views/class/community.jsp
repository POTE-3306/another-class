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
<html>
<head>
    <title>Title</title>
</head>
<%
    List<Post> postList = (List<Post>) request.getAttribute("postList");
    String classId = (String) request.getAttribute("classId");
    Integer pageType = (Integer) request.getAttribute("pageType");
    User user = (User) request.getAttribute("user");
%>
<body>

<div>
    <a href=<%=String.format("/another-class/lecture/%s", classId)%>>수업 페이지로 가기</a>
</div>
<div>
    <a href="material">수업자료</a>
    <a href="notice">공지방</a>
    <a href="talk">수다방</a>
    <a href="task">과제방</a>
</div>

<% if (user.isAdmin()) {%>
<div>
    <ul>
        <% if (pageType == 1) {%>
        <li>
            <a href="#">
                <button type="button">공지생성</button>
            </a>
        </li>
        <% } else if (pageType == 2) { %>
        <li>
            <a href="#">
                <button type="button">과제 생성</button>
            </a>
        </li>
        <% } else if (pageType == 3) { %>
        <li>
            <a href="#">
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
            <a href="#">
                <button type="button">수다방 방 생성</button>
            </a>
        </li>
    </ul>
    <%
    } %>
</div>
<div>
    <ul>
        <% for (Post post : postList) { %>
        <li style=" border: 2px solid rgba(61,49,49,0.95);
                                        border-radius: 10px;
                                        padding: 15px;
                                        box-shadow: 0px 0px 10px #999;
                                        background-color: rgba(255,255,255,0.83);
            ">
            <a href="#">
                <div>
                    <a href=<%=String.format("/another-class/post/%d?boardType=%s", post.getId(),post.getBoardType())%>>
                        <h3><%=post.getTitle()%>
                        </h3>
                            <%
                    String content = post.getContent();
                    String mainContent = new String();
                    if (content.length()>50){
                        mainContent = content.substring(0,50) + "...";
                    }else mainContent=content;
                    %>
                        <p><%=mainContent%>
                        </p>
                </div>
            </a>
        </li>
        <% } %>

    </ul>
</div>
</body>
</html>
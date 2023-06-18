<%--
  Created by IntelliJ IDEA.
  User: Kim
  Date: 2023-06-18
  Time: 오후 3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String classId = (String) request.getAttribute("classId");
    Integer pageType = (Integer) request.getAttribute("pageType");
%>

<html>
<head>
    <title>MAKE Post</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="/another-class/class/assets/css/main.css"/>
</head>
<body class="is-preload">
<div id="wrapper">
    <div id="main">
        <div class="inner">
            <section>
                <div class="row gtr-200">
                    <div class="col-4 col-12-medium">
                    </div>
                    <div class="col-4 col-12-medium">
                        <h1>게시글 작성</h1>
                        <form action="<%= String.format("insert-post") %>" method="post">
                            <div class="row gtr-uniform">
                                <div class="col-12">
                                    <h3>강의 생성</h3>
                                </div>
                                <div class="col-12">
                                    <label for="pageType"></label>
                                    <input type="hidden" id="pageType" name="pageType" value="<%= pageType%>">
                                </div>
                            </div>
                            <div class="col-12">
                                <label for="title">제목:</label>
                                <input type="text" id="title" name="title" required>
                            </div>
                            <div class="col-12">
                                <label for="content">내용:</label>
                                <textarea id="content" name="content" required></textarea>
                            </div>
                            <br/>
                            <div class="col-12">
                                <ul class="actions">
                                    <button type="submit">Submit</button>
                                </ul>
                                <div class="col-4 col-12-medium">
                                    </ul>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-4 col-12-medium">
                    </div>
                </div>
            </section>
        </div>
    </div>
</div>
</body>
</html>

<%--<style>--%>
<%--    /* styles.css */--%>

<%--    .container {--%>
<%--        max-width: 600px;--%>
<%--        margin: 0 auto;--%>
<%--        padding: 20px;--%>
<%--        background-color: #f8f9fa;--%>
<%--    }--%>

<%--    h1 {--%>
<%--        text-align: center;--%>
<%--    }--%>

<%--    .form-group {--%>
<%--        margin-bottom: 20px;--%>
<%--    }--%>

<%--    label {--%>
<%--        display: block;--%>
<%--        font-weight: bold;--%>
<%--    }--%>

<%--    input[type="file"],--%>
<%--    input[type="text"],--%>
<%--    textarea {--%>
<%--        width: 100%;--%>
<%--        padding: 10px;--%>
<%--        font-size: 16px;--%>
<%--        border: 1px solid #ccc;--%>
<%--        border-radius: 4px;--%>
<%--    }--%>

<%--    button[type="submit"] {--%>
<%--        padding: 10px 20px;--%>
<%--        font-size: 16px;--%>
<%--        background-color: #007bff;--%>
<%--        color: #fff;--%>
<%--        border: none;--%>
<%--        border-radius: 4px;--%>
<%--        cursor: pointer;--%>
<%--    }--%>

<%--    button[type="submit"]:hover {--%>
<%--        background-color: #0056b3;--%>
<%--    }--%>
<%--</style>--%>

<%--
  Created by IntelliJ IDEA.
  User: Kim
  Date: 2023-06-15
  Time: 오전 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- mypage.jsp -->

<!DOCTYPE html>

<html>

<head>
    <title>Make Class</title>
</head>
<body>
<div class="container">
    <h1>강의 운영 계획 작성</h1>
    <form action="/courseplan/insert-plan" method="post">
        <div class="form-group">
            <label for="title">강의 운영 계획:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-group">
            <label for="content">강의 세부 내용:</label>
            <textarea id="content" name="content" required></textarea>
        </div>
        <div class="form-group">
            <button type="submit">Submit</button>
        </div>
    </form>
</div>
</body>
</html>

<style>
    /* styles.css */

    .container {
        max-width: 600px;
        margin: 0 auto;
        padding: 20px;
        background-color: #f8f9fa;
    }

    h1 {
        text-align: center;
    }

    .form-group {
        margin-bottom: 20px;
    }

    label {
        display: block;
        font-weight: bold;
    }

    input[type="file"],
    input[type="text"],
    textarea {
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    button[type="submit"] {
        padding: 10px 20px;
        font-size: 16px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button[type="submit"]:hover {
        background-color: #0056b3;
    }
</style>
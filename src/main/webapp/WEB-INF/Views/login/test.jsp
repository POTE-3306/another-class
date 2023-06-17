<%@ page import="simple.project.user.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
%>
<html>
<head>
    <title>User List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
        .button {
            margin-top: 20px;
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
        }
    </style>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Email</th>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <% for (User user : userList) { %>
    <tr onclick="goToUserDetail('<%= user.getId() %>')">
        <td><%= user.getId() %></td>
        <td><%= user.getEmail() %></td>
        <td><%= user.getName() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
<button class="button" onclick="insertTest1()">선생 계정 생성</button>
<button class="button" onclick="insertTest2()">학생 계정 생성</button>
</body>
<script>
    function goToUserDetail(userId) {
        window.location.href = "/another-class/user/test/" + userId;
    }

    function insertTest1() {
        fetch('/another-class/user/test/teacher', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (!response.ok) {
                    alert("에러발생. 다시 시도해주세요.")
                } else {
                    alert("등록 성공!");
                    window.location.reload();
                }
            })
            .catch(() => {
                alert("에러발생. 다시 시도해주세요.")
            });
    }

    function insertTest2() {
        fetch('/another-class/user/test/student', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                if (!response.ok) {
                    alert("에러발생. 다시 시도해주세요.")
                } else {
                    alert("등록 성공!");
                    window.location.reload();
                }
            })
            .catch(() => {
                alert("에러발생. 다시 시도해주세요.")
            });
    }
</script>
</html>
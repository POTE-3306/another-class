<%@ page import="simple.project.user.User" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    List<User> userList = (List<User>) request.getAttribute("userList");
%>
<html>
<head>
    <title>Sign up</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="/another-class/class/assets/css/main.css" />
</head>
<body class="is-preload">
    <div id="wrapper">
        <div id="main">
            <div class="inner">
                <section>
                    <div class="table-wrapper">
                        <table class="alt">
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
                    </div>
                    <button class="button" onclick="insertTest1()">선생 계정 생성</button>
                    <button class="button" onclick="insertTest2()">학생 계정 생성</button>
                </section>
            </div>
        </div>
    </div>
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
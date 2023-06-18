<%@ page import="simple.project.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: msoo6
  Date: 2023-06-15
  Time: 오후 4:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
    HashMap<String, Integer> rateMap = (HashMap<String, Integer>) request.getAttribute("rate");
%>
<html>
<head>
    <title>MyPage</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="/another-class/class/assets/css/main.css"/>
    <script>
        function goBack() {
            window.history.back(); // 뒤로가기 기능 실행
        }
    </script>
</head>
<body class="is-preload">
<div id="wrapper">
    <div id="main">
        <div class="inner">
            <section id="banner">
                <div class="content">
                    <h1>My Page</h1>
                    <div class="box">
                        <p>이름</p>
                        <h3><%=user.getName()%>
                        </h3>
                        <br/>
                        <p>나이</p>
                        <h3><%=user.getAge()%>
                        </h3>
                        <br/>
                        <p>성별</p>
                        <h3><%=user.getGender()%>
                        </h3>
                        <% if (!user.isAdmin()) {%>
                        <hr/>
                        <h3>강의별 출석률</h3>
                        <table>
                            <% for (String courseName : rateMap.keySet()) { %>
                            <tr>
                                <td><%= courseName %>
                                </td>
                                <td><%= rateMap.get(courseName) %> %</td>
                            </tr>
                            <% } %>
                        </table>
                        <%
                            } %>

                    </div>
                    <button onclick="goBack()">뒤로가기</button>
                </div>
            </section>
        </div>
    </div>
</div>
<script src="/another-class/class/assets/js/jquery.min.js"></script>
<script src="/another-class/class/assets/js/browser.min.js"></script>
<script src="/another-class/class/assets/js/breakpoints.min.js"></script>
<script src="/another-class/class/assets/js/util.js"></script>
<script src="/another-class/class/assets/js/main.js"></script>
</body>
</html>

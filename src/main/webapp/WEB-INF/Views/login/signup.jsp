<%@ page import="simple.project.user.APIUserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    APIUserDTO apiUser = (APIUserDTO) request.getAttribute("apiUser");
    if (apiUser == null) {
        response.sendRedirect("index.jsp");
        return ;
    }
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
                    <div class="row gtr-200">
                        <div class="col-4 col-12-medium">
                        </div>
                        <div class="col-4 col-12-medium">
                        <form action="signup" method="post" accept-charset="UTF-8">
                            <div class="row gtr-uniform">
                                <div class="col-12">
                                    <h3>회원가입</h3>
                                </div>
                                <div class="col-6 col-12-xsmall">
                                    <label for="name">이름</label>
                                    <input type="text" name="name" id="name" value="<%= apiUser.getName() %>" readonly />
                                </div>
                                <div class="col-6 col-12-xsmall">
                                    <label for="email">이메일</label>
                                    <input type="email" name="email" id="email" value="<%= apiUser.getEmail() %>" readonly />
                                </div>
                                <div class="col-12">
                                    <label for="age">나이</label>
                                    <input type="number" id="age" name="age" value="<%= apiUser.getAge() %>" readonly />
                                </div>
                                <div class="col-6 col-12-small">
                                    <input type="radio" id="male" name="gender" value="M" <%= apiUser.getGender() == 'M' ? "checked" : "" %> >
                                    <label for="male">남</label>
                                </div>
                                <div class="col-6 col-12-small">
                                    <input type="radio" id="female" name="gender" value="F" <%= apiUser.getGender() == 'F' ? "checked" : "" %> >
                                    <label for="female">여</label>
                                </div>
                                <div class="col-6 col-12-small">
                                    <input type="radio" id="teacher" name="admin" value="true" >
                                    <label for="teacher">선생</label>
                                </div>
                                <div class="col-6 col-12-small">
                                    <input type="radio" id="student" name="admin" value="false" >
                                    <label for="student">학생</label>
                                </div>
                                <input type="hidden" id="naverId" name="naverId" value="<%= apiUser.getNaverId() %>" >
                                <div class="col-12">
                                    <ul class="actions">
                                        <li><input type="submit" value="제출" class="primary" /></li>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    $(document).ready(function() {
        $("input[type=radio][name=gender]").on("click", function(e) {
            e.preventDefault();
        });
    });
</script>
</html>
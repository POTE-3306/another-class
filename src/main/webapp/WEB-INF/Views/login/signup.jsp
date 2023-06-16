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
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            background: #f6f5f7;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            font-family: 'Montserrat', sans-serif;
            height: 100vh;
            margin: -20px 0 50px;
        }

        h2 {
            font-weight: bold;
            margin: 0;
        }

        form {
            margin-top: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        input[type="text"],
        input[type="number"] {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
            width: 300px;
        }

        select {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
            width: 300px;
            height: 40px;
        }

        .gender-selection {
            display: flex;
            justify-content: space-around;
            width: 300px;
            padding: 0; /* padding 제거 */
            border-radius: 5px;
            border: 1px solid #ccc;
            margin-bottom: 10px;
        }

        .gender-option {
            flex: 1; /* 각 옵션에 동일한 공간 할당 */
            display: flex; /* 옵션 내부도 flex로 설정 */
        }

        .gender-option input[type="radio"] {
            display: none;
        }

        .gender-option span {
            flex: 1; /* span에도 flex 1을 적용해 전체 공간을 차지하게 만듦 */
            display: flex; /* span 내부를 flex로 설정 */
            justify-content: center; /* 텍스트를 가운데로 정렬 */
            align-items: center; /* 텍스트를 상하 가운데로 정렬 */
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }

        .gender-option input[type="radio"]:checked + span {
            background-color: #CDDBF6;
            color: #FFFFFF;
        }

        button {
            border-radius: 20px;
            border: 1px solid #CCAAFF;
            background-color: #CCAAFF;
            color: #FFFFFF;
            font-size: 12px;
            font-weight: bold;
            padding: 12px 45px;
            letter-spacing: 1px;
            text-transform: uppercase;
            transition: transform 80ms ease-in;
            cursor: pointer;
        }

        button:active {
            transform: scale(0.95);
        }

        button:focus {
            outline: none;
        }
    </style>
</head>
<body>
<h2>회원가입</h2>
<form action="signup" method="post" accept-charset="UTF-8">
    <input type="text" id="name" name="name" value="<%= apiUser.getName() %>" readonly>
    <input type="text" id="email" name="email" value="<%= apiUser.getEmail()%>" readonly>
    <input type="number" id="age" name="age" value="<%= apiUser.getAge() %>" readonly>
    <div class="gender-selection">
        <label class="gender-option">
            <input type="radio" id="male" name="gender" value="M" <%= apiUser.getGender() == 'M' ? "checked" : "" %> >
            <span>남</span>
        </label>
        <label class="gender-option">
            <input type="radio" id="female" name="gender" value="F" <%= apiUser.getGender() == 'F' ? "checked" : "" %> >
            <span>여</span>
        </label>
    </div>
    <div class="gender-selection">
        <label class="gender-option">
            <input type="radio" id="teacher" name="admin" value="true" >
            <span>선생</span>
        </label>
        <label class="gender-option">
            <input type="radio" id="student" name="admin" value="false" >
            <span>학생</span>
        </label>
    </div>
    <input type="hidden" id="naverId" name="naverId" value="<%= apiUser.getNaverId() %>" >
    <button type="submit" class="insert-button">가입</button>
</form>
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
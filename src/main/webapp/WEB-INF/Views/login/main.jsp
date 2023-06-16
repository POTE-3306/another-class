<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Another Class</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="/another-class/login/assets/css/main.css" />
    <noscript><link rel="stylesheet" href="/another-class/login/assets/css/noscript.css" /></noscript>
</head>
<%
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    apiURL += "&client_id=1F6dYqHfuH3HZA1vc1Ux";
    apiURL += "&redirect_uri=http://localhost:8080/another-class/t3";
    apiURL += "&state=" + state;
%>
<body class="landing is-preload">

    <div id="page-wrapper">

        <header id="header" class="alt">
        </header>

        <section id="banner">
            <div class="inner">
                <h2>Another Class</h2>
                <p>비교할 수 없는 경험을 선사합니다<br />
                    네이버 계정으로 로그인하여 Another Class의 세계를 탐험해 보세요.<br /></p>
                <ul class="actions special">
                    <li><a href="<%=apiURL%>" class="button primary" id="signUp">Naver Login</a></li>
                </ul>
            </div>
            <a href="#one" class="more scrolly">Learn More</a>
        </section>

        <section id="one" class="wrapper style1 special">
            <div class="inner">
                <header class="major">
                    <h2>어나더 클래스는 기존과 다른 경험을 제공합니다.</h2>
                    <p>
                        어나더 클래스는 단순히 온라인 교육 공간을 의미하는 것이 아닙니다. <br />
                        우리는 이 이름에 맞게 전례 없는 독특하고 혁신적인 학습 경험을 제공합니다. <br />
                        우리는 학생들에게 고유의 가치와 능력을 발견하고 성장할 수 있는 새로운 기회를 제공합니다. <br />
                        어나더 클래스는 비교 불가의 독특함을 지닌 교육의 새로운 차원입니다.
                    </p>
                </header>
            </div>
            <ul class="icons major">
                <li><span class="icon solid fa-rocket major style1"><span class="label">Innovation</span></span></li>
                <li><span class="icon solid fa-book major style2"><span class="label">Education</span></span></li>
                <li><span class="icon solid fa-laptop-code major style3"><span class="label">Technology</span></span></li>
            </ul>
        </section>

        <footer id="footer">
            <ul class="icons">
                <li><a href="#footer" class="icon brands fa-twitter"><span class="label">Twitter</span></a></li>
                <li><a href="#footer" class="icon brands fa-facebook-f"><span class="label">Facebook</span></a></li>
                <li><a href="#footer" class="icon brands fa-instagram"><span class="label">Instagram</span></a></li>
                <li><a href="#footer" class="icon brands fa-dribbble"><span class="label">Dribbble</span></a></li>
                <li><a href="mailto:tpgns5888@gmail.com" class="icon solid fa-envelope"><span class="label">Email</span></a></li>
            </ul>
            <ul class="copyright">
                <li>Contact Us</li><li>Git Hub: <a href="https://github.com/POTE-3306/another-class">Anothor Class</a></li>
            </ul>
        </footer>
    </div>

    <script src="/another-class/login/assets/js/jquery.min.js"></script>
    <script src="/another-class/login/assets/js/jquery.scrollex.min.js"></script>
    <script src="/another-class/login/assets/js/jquery.scrolly.min.js"></script>
    <script src="/another-class/login/assets/js/browser.min.js"></script>
    <script src="/another-class/login/assets/js/breakpoints.min.js"></script>
    <script src="/another-class/login/assets/js/util.js"></script>
    <script src="/another-class/login/assets/js/main.js"></script>
</body>
</html>
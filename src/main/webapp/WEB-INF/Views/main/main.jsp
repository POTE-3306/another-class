<%@ page import="simple.project.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="simple.project.post.Post" %>
<%@ page import="simple.project.course.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) request.getAttribute("user");
    List<Post> postList = (List<Post>) request.getAttribute("postList");
    List<Course> courseList = (List<Course>) request.getAttribute("courseList");
%>
<html>
<head>
    <title>Another Class</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="/another-class/main/assets/css/main.css"/>
</head>
<body>
<div id="page-wrapper">
<%--    TODO: myPage 추가--%>
    <a href="/another-class/mypage">마이페이지</a>
<%--    선생님일 경우만 강의 생성 링크 활성화--%>
    <% if(user.isAdmin()){%>
        <a href="/another-class/course/make-class">강의생성</a>
    <%}%>


    <!-- Header -->
    <div id="header-wrapper">
        <div class="container">
        </div>
    </div>

    <!-- Banner -->
    <div id="banner-wrapper">
        <div class="container">

            <div id="banner">
                <h2>Put something cool here!</h2>
                <span>And put something almost as cool here, but a bit longer ...</span>
            </div>

        </div>
    </div>

    <div id="main">
        <div class="container">
            <div class="row main-row">
                <div class="col-10 col-12-medium">
                    <section>
                        <ul class="big-image-list">
                            <% for (Course course:
                                    courseList) { %>
                            <li>
                                <img src="images/pic3.jpg" alt="" class="left" />
                                <a href=<%="t5/" + course.getId()%>> <div>
                                    <h3><%=course.getName()%></h3>
                                    <p><%=course.getDescription()%></p>
                                </div> </a>
                            </li>

                            <%} %>
                        </ul>
                    </section>
                </div>
                <div class="col-2 col-12-medium">
                    <section>
                        <h2>How about some links?</h2>
                        <div class="row">
                            <ul class="link-list">
                                <li><a href="#">Sed neque nisi consequat</a></li>
                                <li><a href="#">Dapibus sed mattis blandit</a></li>
                                <li><a href="#">Quis accumsan lorem</a></li>
                                <li><a href="#">Suspendisse varius ipsum</a></li>
                                <li><a href="#">Eget et amet consequat</a></li>
                            </ul>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>

    <div id="footer-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-4 col-12-medium">
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <div id="copyright">
                        <ol>
                            <li> Contact Us Git Hub: <a href="https://github.com/POTE-3306/another-class">Another Class</a></li>
                        </ol>
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

<script src="/another-class/main/assets/js/jquery.min.js"></script>
<script src="/another-class/main/assets/js/browser.min.js"></script>
<script src="/another-class/main/assets/js/breakpoints.min.js"></script>
<script src="/another-class/main/assets/js/util.js"></script>
<script src="/another-class/main/assets/js/main.js"></script>

</body>
</html>

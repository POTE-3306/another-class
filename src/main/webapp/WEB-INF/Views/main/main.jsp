<%@ page import="simple.project.user.User" %>
<%@ page import="simple.project.post.Post" %>
<%@ page import="java.util.List" %>
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
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="/another-class/main/assets/css/main.css"/>

</head>
<body>

<div id="page-wrapper">

    <div id="header-wrapper">
        <div class="container">
        </div>
    </div>

    <div id="banner-wrapper">
        <div class="container">
            <div id="banner">
                <h3> CLASS ID </h3>
                <form id="myForm">
                    <input type="text" id="uuidInput" placeholder="Class ID">
                    <button type="submit">등록하기</button>
                </form>
            </div>
        </div>
    </div>

    <div id="main">
        <div class="container">
            <div class="row main-row">
                <div class="col-10 col-12-medium" style="text-align: right;">
                    <button onclick="window.location.href='/another-class/course/make-class'" style="
                                                         border-radius: 5px;
                                                         border: none;
                                                         color: #fff;
                                                         background-color: #007b9d;
                                                         padding: 10px 20px;
                ">강의 생성
                    </button>
                </div>
                <div class="col-2 col-12-medium">
                </div>
            </div>
            <br/>
            <br/>
            <div class="row main-row">
                <div class="col-10 col-12-medium">
                    <section>
                        <ul class="small-image-list">
                            <%
                                for (Course course : courseList) {
                            %>
                            <li style=" border: 2px solid rgba(61,49,49,0.95);
                                        border-radius: 10px;
                                        padding: 15px;
                                        box-shadow: 0px 0px 10px #999;
                                        background-color: rgba(255,255,255,0.83);
                                ">
                                <img src="/another-class/main/assets/image/<%=course.getLogo_url()%>" alt=""
                                     class="left"/>
                                <a href="#">
                                    <div>
                                        <a href=<%=String.format("/another-class/lecture/%d", course.getId())%>>
                                            <h3><%=course.getName()%>
                                            </h3>
                                            <p><%=course.getDescription()%>
                                            </p>
                                    </div>
                                </a>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </section>
                </div>
                <div class="col-2 col-12-medium">
                    <section style="border: 2px solid rgba(61,49,49,0.95);
                                    border-radius: 10px;
                                    padding: 15px;
                                    box-shadow: 0px 0px 10px #999;
                                    background-color: rgba(255,255,255,0.83);
                        ">
                        <h2>내 게시물</h2>
                        <div class="row">
                            <ul class="link-list">
                                <%
                                    for (Post post : postList) {

                                %>
                               <form method="post" action="<%= post.getId() %>">
                                <li>
                                    <button type="submit" style="border: none; background: none; padding: 0;">
                                        <%= post.getTitle() %>
                                    </button>
                                    <input type="hidden" name="boardType" value="<%= post.getBoardType()%>">
                                </li>
                            </form>
                                <%
                                    }
                                %>
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
                            <li> Contact Us Git Hub: <a href="https://github.com/POTE-3306/another-class">Another
                                Class</a></li>
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

<script>
    document.getElementById('myForm').addEventListener('submit', function (e) {
        e.preventDefault();
        let url = document.getElementById('uuidInput').value;
        fetch('/another-class/register/' + url, {
            method: 'GET',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => {
                switch (response.status) {
                    case 200:
                        alert("등록 성공!");
                        window.location.reload();
                        break;
                    case 400:
                        alert("CLASS ID가 존재하지 않습니다.");
                        break;
                    case 422:
                        alert("이미 등록하셨습니다.");
                        break;
                    default:
                        alert("에러발생. 다시 시도해주세요.");
                        break;
                }
            })
            .catch(() => {
                alert("에러발생. 다시 시도해주세요.")
            });
    });

    function getBoardType(boardOption) {
        var boardType;
        switch (boardOption) {
            case 'ASSIGNMENT':
                boardType = 2;
                break;
            case 'MATERIAL':
                boardType = 3;
                break;
            case 'CHAT':
                boardType = 4;
                break;
            default:
                boardType = 1;
                break;
        }
        return boardType;
    }
</script>

</body>
</html>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int courseId = (int) request.getAttribute("courseId");
%>

<html>
<head>
    <title>MAKE CLASS</title>
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
                        <form action="/another-class/course/insert-plan" method="post" accept-charset="UTF-8">
                            <div class="row gtr-uniform">
                                <div class="col-12">
                                    <h3>강의 계획</h3>
                                </div>
                                <div class="col-12">
                                    <label for="title">제목</label>
                                    <input type="text" id="title" name="title" required>
                                </div>
                                <div class="col-12">
                                    <label for="content">설명</label>
                                    <textarea id="content" name="content" required></textarea>
                                </div>
                                <input type="hidden" id="courseId" name="courseId" value="<%= courseId %>">
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
</html>
<%@ page import="simple.project.user.User" %>
<%@ page import="simple.project.course.Course" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Course course = (Course) request.getAttribute("course");
  int courseId = course.getId();
%>
<html>
<head>
  <title>Modify CLASS</title>
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
            <form action="/another-class/course/modify-class" method="post" accept-charset="UTF-8">
              <div class="row gtr-uniform">
                <div class="col-12">
                  <h3>강의 수정</h3>
                </div>
                <div class="col-12">
                  <label for="title">제목</label>
                  <input type="text" id="title" name="title" value="<%=course.getName()%>" required>
                </div>
                <div class="col-12">
                  <label for="content">강의 설명</label>
                  <textarea id="content" name="content" required><%=course.getDescription()%></textarea>
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
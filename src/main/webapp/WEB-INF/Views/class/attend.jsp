<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: msoo6
  Date: 2023-06-15
  Time: 오후 1:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  HashMap<Integer, ArrayList<String>> map
          = (HashMap<Integer, ArrayList<String>>) request.getAttribute("atendMap");
%>
<html>
<head>
  <title>ClassPage</title>
</head>
<body>
<%
  String url = request.getRequestURI();
  String path = request.getContextPath();
%>
<h1>ClassPage</h1>
<h4><%=(String) request.getAttribute("classId")%></h4>
<a href="#">mypage</a>
<a href="#">게시글리스트</a>
<a href="#">강의페이지</a>
<a href="#"></a>
<%
  ArrayList<String> list = new ArrayList<>();

  for (int userId : map.keySet()) {
    System.out.print(userId + " : ");
    list = map.get(userId);
    System.out.println(list.get(0) +" "+list.get(1) +" "+list.get(2));
  }
%>

</body>
</html>

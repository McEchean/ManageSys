<%--
  Created by IntelliJ IDEA.
  User: 98382
  Date: 2018/5/30
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>First</title>
  </head>
  <body>
  <%
//      response.sendRedirect("/ManagerLogin/WEB-INF/admin/Login.jsp");
      request.getRequestDispatcher("/admin/Login.jsp").forward(request,response);
  %>
  </body>
</html>

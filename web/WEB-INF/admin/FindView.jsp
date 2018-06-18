<%@ page import="java.util.ArrayList" %>
<%@ page import="com.eachen.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: 98382
  Date: 2018/6/18
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查找用户</title>
</head>
<%
    ArrayList arr = (ArrayList) request.getAttribute("result");
    User newu = (User) request.getSession().getAttribute("loginUser");
    if (newu == null) {
        request.setAttribute("msg", "请先登录");
        request.getRequestDispatcher("/Login").forward(request, response);
        return;
    }
%>
<body>
<img src='./1.jpg'/>欢迎<%=newu.getUsername() %>登录 &nbsp&nbsp
<a href='/ManagerLogin/mainFrame'> 返回主页面</a>&nbsp&nbsp&nbsp
<a href='/ManagerLogin/FindCl?type=out'>安全退出</a>
<hr/>
<h1>查找用户</h1>
<form action='/ManagerLogin/FindCl' method='post'>
    输入用户ID：<input type='text' name='id'/>
    <input type='checkbox' name='style'>模糊查询<br/>
    <input type='submit' value='查询'>
</form>
<%
    if (arr != null) {
%>

<table border=1px bordercolor=green width=500px>
    <tr>
        <th>用户ID</th>
        <th>用户名</th>
        <th>Email</th>
        <th>Grade</th>
    </tr>
    <%
        for (Object u : arr) {
            User user = (User) u;
    %>

    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getUsername()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <td><%=user.getGrade()%>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
<hr/>
<img src='./2.jpg'/>


</body>
</html>

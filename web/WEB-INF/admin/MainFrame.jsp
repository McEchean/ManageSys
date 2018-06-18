<%@ page import="com.eachen.domain.User" %>
<%@ page import="javafx.application.Application" %>
<%--
  Created by IntelliJ IDEA.
  User: 98382
  Date: 2018/6/17
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎</title>
</head>
<%
    String id = request.getParameter("id");
    String visitnum = (String) application.getAttribute("visitnum");

    // 取出session
    User u = (User) request.getSession().getAttribute("loginUser");
    if( u == null ){
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/admin/Login.jsp").forward(request,response);
        return;
    }
%>
<body>
    <img src='./1.jpg'/>欢迎<%=u.getUsername() %>登录 &nbsp&nbsp&nbsp
    <a href='/ManagerLogin/FindCl?type=out'>安全退出</a><hr/>
    <p>你是第<%=visitnum %>个访问本网站</p>
    <h3>请选择你要进行的操作</h3>
    <a href='/ManagerLogin/manageUsersCl'><管理用户></a><br/>
    <a href='/ManagerLogin/admin/addUser.jsp?id=<%=id%>'><添加用户></a><br/>
    <a href='/ManagerLogin/FindCl?type=gotoFindView'><查找用户></a><br/>
    <a href='/ManagerLogin/FindCl?type=out'><退出系统></a><br/><hr/>
    <img src='./2.jpg'/>
</body>
</html>

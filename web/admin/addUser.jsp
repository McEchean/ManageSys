<%@ page import="com.eachen.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: 98382
  Date: 2018/6/18
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>增加用户</title>
</head>
<%
    User u = (User) request.getSession().getAttribute("loginUser");
    if( u == null ){
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/admin/Login.jsp").forward(request,response);
        return;
    }
%>
<body>
<img src='/ManagerLogin/1.jpg'/>欢迎<%=u.getUsername() %>登录&nbsp&nbsp&nbsp
<a href='/ManagerLogin/admin/MainFrame.jsp'> 返回主页面</a>&nbsp&nbsp&nbsp
<a href='/ManagerLogin/FindCl?type=out'>安全退出</a><hr/>
<h1>添加用户</h1>
<form action='/ManagerLogin/adsmService?type=add' method='post'>
    <table border=1px bordercolor=green width=300px>
        <tr><td>用户id</td><td><input type='text' name='id'></td></tr>
        <tr><td>用户名</td><td><input type='text' name='username'></td></tr>
        <tr><td>email</td><td><input type='text' name='email'></td></tr>
        <tr><td>等级</td><td><input type='text' name='grade'></td></tr>
        <tr><td>密码</td><td><input type='password' name='password'></td></tr>
        <tr><td>添加</td><td><input onClick='return confirmAdd();' type='submit' value='添加'></td></tr>
    </table>
</form><hr/>
<img src='/ManagerLogin/2.jpg'/>


</body>
<script type='text/javascript' language='javascript'>
    function confirmAdd(){return window.confirm('确认提交？');}
</script>
</html>

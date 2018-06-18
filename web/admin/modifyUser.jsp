<%@ page import="com.eachen.domain.User" %>
<%@ page import="com.eachen.servive.UserSevive" %><%--
  Created by IntelliJ IDEA.
  User: 98382
  Date: 2018/6/18
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户资料</title>
</head>
<%
    User u = (User) request.getSession().getAttribute("loginUser");
    if( u == null ){
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/Login").forward(request,response);
        return;
    }

    String id = request.getParameter("id");
    UserSevive userSevive = new UserSevive();
    User newUser = new User(Integer.parseInt(id));
    newUser = (User)userSevive.getOneObj(newUser);
%>
<body>
<img src='./1.jpg'/>欢迎<%=u.getUsername()%>登录&nbsp&nbsp
<a href='/ManagerLogin/admin/MainFrame.jsp'> 返回主页面</a>&nbsp&nbsp&nbsp
<a href='/ManagerLogin/FindCl?type=out'>安全退出</a><hr/>
<h1>修改用户</h1>
<form action='/ManagerLogin/adsmService?type=modify&id=<%=newUser.getId()%>' method='post' >
    <table border=1px bordercolor=green width=300px>
        <tr><td>用户id </td><td><input type='text' name='newid' readonly value='<%=newUser.getId() %>'></td></tr>
        <tr><td>用户名 </td><td><input type='text' name='newusername' value='<%=newUser.getUsername() %>'></td></tr>
        <tr><td>email</td><td><input type='text' name='newemail' value='<%=newUser.getEmail() %>'></td></tr>
        <tr><td>等  级</td><td><input type='text' name='newgrade' value='<%=newUser.getGrade() %>'></td></tr>
        <tr><td>密  码</td><td><input type='password' name='newpassword' value='<%=newUser.getPassowrd() %>'></td></tr>
        <tr><td>修  改</td><td><input onClick='return confirmModify();' type='submit' value='提交'></td></tr>
    </table>
</form>
<hr/>
<img src='./2.jpg'/>

</body>
<script type='text/javascript' language='javascript'>
    function confirmModify(){return window.confirm('确认提交？');}
</script>
</html>

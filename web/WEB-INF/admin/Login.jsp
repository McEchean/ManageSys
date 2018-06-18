<%--
  Created by IntelliJ IDEA.
  User: 98382
  Date: 2018/6/17
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%
    String id = "";
    String password = "";

    Cookie[] cookies = request.getCookies();
    if(cookies != null) {
        for (Cookie c: cookies) {
            if("id".equals(c.getName())) {
                id = c.getValue();
            }
            if("password".equals(c.getName())) {
                password = c.getValue();
            }
        }

    }
%>
    <img src='./1.jpg'/><hr/>
    <h1>你好  三脚猫</h1><hr/>
    <form action='/ManagerLogin/LoginCl' method='post'>
        用户名：<input type='text' value ='<%=id %>'name='id'/>
        密  码：<input type='password' value='<%=password%>'name='password'/>
        <input type='checkbox' name='iskeepinfo'/>记住用户名密码<br/>
        <input type='submit' value = '登录'/>
    </form>
    <hr/>
<%
    String msg = (String)request.getAttribute("msg");
    if(msg != null) {
        %>
        <h3><%=msg %></h3>
<%
    }
%>
    <img src='./2.jpg'/>

</body>
</html>

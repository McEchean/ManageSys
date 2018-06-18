<%@ page import="java.util.ArrayList" %>
<%@ page import="com.eachen.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: 98382
  Date: 2018/6/17
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理用户</title>
</head>
<%
    int pageNow = 1;
    if (request.getParameter("pageNow") != null) {
        pageNow = Integer.parseInt(request.getParameter("pageNow"));
    }
    Long pageCount = (Long) request.getAttribute("pagecount");
    User newu = (User) request.getAttribute("newuser");
%>
<body>
<img src='./1.jpg'/>
欢迎<%= newu.getUsername() %>登录 &nbsp&nbsp
<a href='/ManagerLogin/mainFrame'>返回主页面</a>&nbsp&nbsp&nbsp
<a href='/ManagerLogin/FindCl?type=out'>安全退出</a>
<hr/>
<h1>管理用户</h1>
<table border=1px bordercolor=green width=500px>
    <tr>
        <th>用户id</th>
        <th>用户名</th>
        <th>email</th>
        <th>级别</th>
        <th>删除用户</th>
        <th>修改用户</th>
    </tr>
    <%
        ArrayList list = (ArrayList) request.getAttribute("list");
        for (int i = 0; i < list.size(); i++) {
            User u = (User) list.get(i);
    %>
    <tr>
        <td><%=u.getId()%>
        </td>
        <td><%=u.getUsername()%>
        </td>
        <td><%=u.getEmail()%>
        </td>
        <td><%=u.getGrade()%>
        </td>
        <td><a onClick='return confirmOper();' href='/ManagerLogin/adsmService?type=del&id="+ u.getId() +"'>删除用户</a>
        </td>
        <td><a href='/ManagerLogin/WEB-INF/admin/modifyUser.jsp?id="+ u.getId() +"'>修改用户</a></td>
    </tr>
    <%
        }
    %>

</table>
<%
    if (pageNow != 1) {
%>
<a href='/ManagerLogin/manageUsersCl?pageNow=1'>
    <首页>
</a>
<%
    }
%>

<%
    if ((pageNow - 1) > 0) {
%>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=pageNow - 1 %>'>
    <上一页>
</a>
<%
    }
%>
<%
    if (pageCount > 6) {
        if (pageNow > 2) {
%>
...
<%
    }
    if (pageNow >= 2 && pageNow <= pageCount - 1) {
%>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=(pageNow-1)%>'><<%=(pageNow - 1)%>></a>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=(pageNow)%>'><<%=(pageNow)%>></a>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=(pageNow+1)%>'><<%=(pageNow + 1)%>></a>
<%
} else if (pageNow == 1) {
%>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=pageNow%>'><<%=pageNow%>></a>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=pageNow + 1%>'><<%=pageNow + 1%>></a>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=pageNow + 2%>'><<%=pageNow + 2%>></a>
<%
} else {
%>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=pageNow - 2%>'><<%=pageNow - 2%>></a>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=pageNow - 1%>'><<%=pageNow - 1%>></a>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=pageNow%>'><<%=pageNow%>></a>
<%
    }
    if (pageNow < pageCount - 1) {
%>
...
<%
    }
} else {
    for (int i = 1; i <= pageCount; i++) {
%>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=i%>'><<%=i%>></a>
<%
        }
    }

    if ((pageNow + 1) <= pageCount) {
%>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=pageNow + 1%>'>
    <下一页>
</a>
<%
    }
    if (pageNow != pageCount) {
%>
<a href='/ManagerLogin/manageUsersCl?pageNow=<%=pageCount%>'>
    <末页>
</a>
<%
    }
%>

&nbsp;&nbsp;&nbsp当前页<%=pageNow%>/共<%=pageCount%>页<br/>
<hr/>
<img src='./2.jpg'/>


</body>
<script type='text/javascript' language='javascript'>
    function confirmOper() {
        return window.confirm('真的要删除该用户吗？');
    }
</script>
</html>

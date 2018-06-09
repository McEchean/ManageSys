package com.eachen.view;

import com.eachen.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addUser",urlPatterns = "/addUser")
public class addUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");

        User u = (User) request.getSession().getAttribute("loginUser");
        if( u == null ){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/Login").forward(request,response);
            return;
        }
        out.println("<script type='text/javascript' language='javascript'>");
        out.println("function confirmAdd(){return window.confirm('确认提交？');}");
        out.println("</script>");

        out.println("<img src='./1.jpg'/>欢迎" + u.getUsername() + "登录&nbsp&nbsp&nbsp  <a href='/ManagerLogin/mainFrame'> 返回主页面</a>" +
                "&nbsp&nbsp&nbsp  <a href='/ManagerLogin/FindCl?type=out'>安全退出</a><hr/>");
        out.println("<h1>添加用户</h1>");
        out.println("<form action='/ManagerLogin/adsmService?type=add' method='post' >");
        out.println("<table border=1px bordercolor=green width=300px>");
        out.println("<tr><td>用户id</td><td><input type='text' name='id'></td></tr>");
        out.println("<tr><td>用户名</td><td><input type='text' name='username'></td></tr>");
        out.println("<tr><td>email</td><td><input type='text' name='email'></td></tr>");
        out.println("<tr><td>等级</td><td><input type='text' name='grade'></td></tr>");
        out.println("<tr><td>密码</td><td><input type='password' name='password'></td></tr>");
        out.println("<tr><td>添加</td><td><input onClick='return confirmAdd();' type='submit' value='添加'></td></tr>");

        /*out.println("用户id:<input type='text' name='id'>");
        out.println("用户名:<input type='text' name='username'>");
        out.println("emial:<input type='text' name='email'>");
        out.println("等级:<input type='text' name='grade'>");
        out.println("密码:<input type='password' name='password'>");
        out.println("<input onClick='return confirmAdd();' type='submit' value='添加'>");*/

        out.println("</table>");
        out.println("</form><hr/>");
        out.println("<img src='./2.jpg'/>");
    }
}

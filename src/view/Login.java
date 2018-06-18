package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Login",urlPatterns = "/Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
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

        PrintWriter out = response.getWriter();
        out.println("<img src='./1.jpg'/><hr/>");
        out.println("<h1>你好  三脚猫</h1><hr/>");
        out.println("<form action='/ManagerLogin/LoginCl' method='post'>");
        out.println("用户名：<input type='text' value ='" + id + "'name='id'/>");
        out.println("密  码：<input type='password' value='" + password + "'name='password'/>");
        out.println("<input type='checkbox' name='iskeepinfo'/>记住用户名密码<br/>");
        out.println("<input type='submit' value = '登录'/>");
        out.println("</form>");
        out.println("<hr/>");
        String msg = (String)request.getAttribute("msg");
        if(msg != null) {
            out.println("<h3>" + msg + "</h3>");
        }
        out.println("<img src='./2.jpg'/>");
    }
}

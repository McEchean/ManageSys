package view;

import com.eachen.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "FindView",urlPatterns = "/FindView")
public class FindView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        ArrayList arr = (ArrayList) request.getAttribute("result");

        User newu = (User) request.getSession().getAttribute("loginUser");
        if( newu == null ){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/Login").forward(request,response);
            return;
        }

        out.println("<img src='./1.jpg'/>欢迎" + newu.getUsername() + "登录 &nbsp&nbsp<a href='/ManagerLogin/mainFrame'> 返回主页面</a>" +
                "&nbsp&nbsp&nbsp  <a href='/ManagerLogin/FindCl?type=out'>安全退出</a><hr/>");
        out.println("<h1>查找用户</h1>");
        out.println("<form action='/ManagerLogin/FindCl' method='post'>");
        out.println("输入用户ID：<input type='text' name='id'/>");
        out.println("<input type='checkbox' name='style'>模糊查询<br/>");
        out.println("<input type='submit' value='查询'>");
        out.println("</form>");
        if(arr != null) {
            out.println("<table border=1px bordercolor=green width=500px>");
            out.println("<tr><th>用户ID</th><th>用户名</th><th>Email</th><th>Grade</th></tr>");
            for (Object u:arr) {
                User user = (User) u;
                out.println("<tr><td>" + user.getId() + "</td>" +
                        "<td>" + user.getUsername() + "</td>" +
                        "<td>" + user.getEmail() + "</td>" +
                        "<td>" + user.getGrade() + "</td></tr>");

            }
            out.println("</table>");
        }
        out.println("<hr/><img src='./2.jpg'/>");
    }
}

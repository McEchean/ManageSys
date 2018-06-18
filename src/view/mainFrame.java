package view;

import com.eachen.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "mainFrame",urlPatterns = "/mainFrame")
public class mainFrame extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        String visitnum = (String) this.getServletContext().getAttribute("visitnum");

        //取出session
        User u = (User) request.getSession().getAttribute("loginUser");
        if( u == null ){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/Login").forward(request,response);
            return;
        }

        out.println("<img src='./1.jpg'/>欢迎" + u.getUsername() + "登录 &nbsp&nbsp&nbsp  <a href='/ManagerLogin/FindCl?type=out'>安全退出</a><hr/>");
        out.println("<p>你是第" + visitnum + "个访问本网站</p>");
        out.println("<h3>请选择你要进行的操作</h3>");
        out.println("<a href='/ManagerLogin/manageUsers'><管理用户></a><br/>");
        out.println("<a href='/ManagerLogin/addUser?id=" + id + "'><添加用户></a><br/>");
        out.println("<a href='/ManagerLogin/FindCl?type=gotoFindView'><查找用户></a><br/>");
        out.println("<a href='/ManagerLogin/FindCl?type=out'><退出系统></a><br/><hr/>");
        out.println("<img src='./2.jpg'/>");
    }
}


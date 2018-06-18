package view;

import com.eachen.domain.User;
import com.eachen.servive.UserSevive;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "manageUsers",urlPatterns = "/manageUsers")
public class manageUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        User newu = (User) request.getSession().getAttribute("loginUser");
        if( newu == null ){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/Login").forward(request,response);
            return;
        }
        out.println("<script type='text/javascript' language='javascript'>");
        out.println("function confirmOper(){return window.confirm('真的要删除该用户吗？');}");

        out.println("</script>");

        String username = request.getParameter("id");
        out.println("<img src='./1.jpg'/>欢迎" + newu.getUsername() + "登录&nbsp&nbsp <a href='/ManagerLogin/mainFrame'> 返回主页面</a>" +
                "&nbsp&nbsp&nbsp  <a href='/ManagerLogin/FindCl?type=out'>安全退出</a><hr/>");
        out.println("<h1>管理用户</h1>");

        int pageNow = 1;
        if(request.getParameter("pageNow") != null) {
            pageNow = Integer.parseInt(request.getParameter("pageNow"));
        }
        int rowCount = 1;
        int pageSize = 4;
        Long pageCount = 1L;


        try {
            UserSevive userSevive = new UserSevive();
            pageCount =  userSevive.getPageCount(pageSize);

            ArrayList list = userSevive.getUsersByPages(pageNow,pageSize);

            out.println("<table border=1px bordercolor=green width=500px>");
            out.println("<tr><th>用户id</th><th>用户名</th><th>email</th><th>级别</th><th>删除用户</th><th>修改用户</th></tr>");
            for(int i = 0; i < list.size();i++) {
                User u = (User) list.get(i);
                out.println("<tr>" +
                        "<td>" + u.getId() + "</td>" +
                        "<td>" + u.getUsername()+ "</td>" +
                        "<td>" + u.getEmail() + "</td>" +
                        "<td>" + u.getGrade() + "</td>" +
                        "<td><a onClick='return confirmOper();' href='/ManagerLogin/adsmService?type=del&id="+ u.getId() +"'>删除用户</a></td>" +
                        "<td><a href='/ManagerLogin/modifyUser?id="+ u.getId() +"'>修改用户</a></td></tr>");
            }
            out.println("</table>");
            if(pageNow != 1) {
                out.println("<a href='/ManagerLogin/manageUsers?pageNow=1'><首页></a>");
            }
            if((pageNow - 1) > 0) {
                out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + (pageNow - 1) + "'><上一页></a>");
            }
            if(pageCount > 6) {
                if(pageNow > 2) {
                    out.println("...");
                }
                if(pageNow >= 2 && pageNow <= pageCount-1) {
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + (pageNow-1) + "'><" + (pageNow-1) + "></a>");
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + pageNow + "'><" + pageNow + "></a>");
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + (pageNow+1) + "'><" + (pageNow+1) + "></a>");
                }else if(pageNow == 1) {
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + pageNow + "'><" + pageNow + "></a>");
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + (pageNow+1) + "'><" + (pageNow+1) + "></a>");
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + (pageNow+2) + "'><" + (pageNow+2) + "></a>");
                }else {
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + (pageNow-2) + "'><" + (pageNow-2) + "></a>");
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + (pageNow-1) + "'><" + (pageNow-1) + "></a>");
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + pageNow + "'><" + pageNow + "></a>");
                }

                if(pageNow < pageCount-1 ) {
                    out.println("...");
                }
            }else {
                for(int i = 1; i <= pageCount;i++) {
                    out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + i + "'><" + i + "></a>");
                }
            }

            if((pageNow + 1) <= pageCount) {
                out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + (pageNow + 1) + "'><下一页></a>");
            }
            if(pageNow != pageCount) {
                out.println("<a href='/ManagerLogin/manageUsers?pageNow=" + pageCount + "'><末页></a>");
            }
            out.println("&nbsp;&nbsp;&nbsp当前页"+ pageNow +"/共"+ pageCount +"页<br/>");
            String msg = (String)request.getAttribute("msg");
            if(msg != null && !"".equals(msg)) {
                out.println("<h4>" + msg + "</h4>");
            }

            out.println("<hr/>");
            out.println("<img src='./2.jpg'/>");
        }catch (Exception e) {
            request.setAttribute("msg","用户名或密码为空");
            request.getRequestDispatcher("/Login").forward(request,response);
            e.printStackTrace();

        }
    }
}

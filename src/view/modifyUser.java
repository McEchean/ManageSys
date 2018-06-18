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

@WebServlet(name = "modifyUser",urlPatterns = "/modifyUser")
public class modifyUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

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

        out.println("<script type='text/javascript' language='javascript'>");
        out.println("function confirmModify(){return window.confirm('确认提交？');}");
        out.println("</script>");

        out.println("<img src='./1.jpg'/>欢迎" + u.getUsername() + "登录&nbsp&nbsp <a href='/ManagerLogin/mainFrame'> 返回主页面</a>" +
                "&nbsp&nbsp&nbsp <a href='/ManagerLogin/FindCl?type=out'>安全退出</a><hr/>");
        out.println("<h1>修改用户</h1>");
        out.println("<form action='/ManagerLogin/adsmService?type=modify&id=" + newUser.getId() + "' method='post' >");
        out.println("<table border=1px bordercolor=green width=300px>");
        out.println("<tr><td>用户id </td><td><input type='text' name='newid' readonly value='"+ newUser.getId() +"'></td></tr>");
        out.println("<tr><td>用户名 </td><td><input type='text' name='newusername' value='"+ newUser.getUsername() +"'></td></tr>");
        out.println("<tr><td>email</td><td><input type='text' name='newemail' value='"+ newUser.getEmail() +"'></td></tr>");
        out.println("<tr><td>等  级</td><td><input type='text' name='newgrade' value='"+ newUser.getGrade() +"'></td></tr>");
        out.println("<tr><td>密  码</td><td><input type='password' name='newpassword' value='"+ newUser.getPassowrd() +"'></td></tr>");
        out.println("<tr><td>修  改</td><td><input onClick='return confirmModify();' type='submit' value='提交'></td></tr>");



        /*out.println("用户id:<input type='text' name='newid' readonly value='"+ newUser.getId() +"'>");
        out.println("用户名:<input type='text' name='newusername' value='"+ newUser.getUsername() +"'>");
        out.println("emial:<input type='text' name='newemail' value='"+ newUser.getEmail() +"'>");
        out.println("等级:<input type='text' name='newgrade' value='"+ newUser.getGrade() +"'>");
        out.println("密码:<input type='password' name='newpassword' value='"+ newUser.getPassowrd() +"'>");
        out.println("<input onClick='return confirmModify();' type='submit' value='提交'>");*/
        out.println("</table>");
        out.println("</form><hr/>");
        out.println("<img src='./2.jpg'/>");
    }
}

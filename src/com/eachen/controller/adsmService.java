package com.eachen.controller;

import com.eachen.domain.User;
import com.eachen.servive.UserSevive;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adsmService", urlPatterns = "/adsmService")
public class adsmService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String type = request.getParameter("type");
        switch(type) {
            case "del":
            {
                String id = request.getParameter("id");

                UserSevive userSevive = new UserSevive();
                if(userSevive.delUser(id)) {
                    request.setAttribute("msg","删除成功!");
                    request.getRequestDispatcher("/manageUsersCl").forward(request,response);
                }else {
                    request.setAttribute("msg","删除失败!");
                    request.getRequestDispatcher("/manageUsersCl").forward(request,response);
                }
                break;
            }
            case "modify":
            {
                String id = request.getParameter("id");
                String newid = request.getParameter("newid");
                String newusername = request.getParameter("newusername");
                System.out.println(newusername);
                String newemail = request.getParameter("newemail");
                String newgrade = request.getParameter("newgrade");
                String newpassword = request.getParameter("newpassword");
                User user = new User(Integer.parseInt(id));
                user.setId(Integer.parseInt(newid));
                user.setUsername(newusername);
                user.setEmail(newemail);
                user.setGrade(Integer.parseInt(newgrade));
                user.setPassowrd(newpassword);
                System.out.println(user);
                UserSevive userSevive = new UserSevive();
                if(userSevive.modiferUser(user)) {
                    request.setAttribute("msg","修改成功!");
                    request.getRequestDispatcher("/manageUsersCl").forward(request,response);
                }
                break;
            }
            case "add":
            {
                User user = new User();
                String id = request.getParameter("id");
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String grade = request.getParameter("grade");
                String password = request.getParameter("password");
                user.setId(Integer.parseInt(id));
                user.setUsername(username);
                user.setEmail(email);
                user.setGrade(Integer.parseInt(grade));
                user.setPassowrd(password);

                UserSevive userSevive = new UserSevive();
                if(userSevive.addUser(user)) {
                    request.setAttribute("msg","添加成功!");
                    request.getRequestDispatcher("/manageUsersCl").forward(request,response);
                }
                break;
            }
            default:
                break;
        }


    }
}

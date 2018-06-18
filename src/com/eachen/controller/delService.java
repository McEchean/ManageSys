package com.eachen.controller;

import com.eachen.servive.UserSevive;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delService", urlPatterns = "/delService")
public class delService extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
                break;
            }
            case "add":
            {
                break;
            }
            default:
                break;
        }


    }
}

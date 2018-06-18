package com.eachen.controller;

import com.eachen.domain.User;
import com.eachen.servive.UserSevive;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "manageUsersCl", urlPatterns = "/manageUsersCl")
public class manageUsersCl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User newu = (User) request.getSession().getAttribute("loginUser");
        if( newu == null ){
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/Login").forward(request,response);
            return;
        }
        UserSevive userSevive = new UserSevive();
        int pageNow = 1;
        if(request.getParameter("pageNow") != null) {
            pageNow = Integer.parseInt(request.getParameter("pageNow"));
        }
        int pageSize = 4;
        Long pagecount = userSevive.getPageCount(pageSize);
        ArrayList list = userSevive.getUsersByPages(pageNow,pageSize);
        request.setAttribute("list",list);
        request.setAttribute("pagecount",pagecount);
        request.setAttribute("newuser",newu);
        request.getRequestDispatcher("/admin/manageUsers.jsp").forward(request,response);
    }
}

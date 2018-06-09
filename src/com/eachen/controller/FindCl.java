package com.eachen.controller;

import com.eachen.servive.UserSevive;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FindCl",urlPatterns = "/FindCl")
public class FindCl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String style = request.getParameter("style");
        String id = request.getParameter("id");
        UserSevive userSevive = new UserSevive();
        ArrayList arr = userSevive.getObjectByS(id,style);
        if(arr != null) {
            request.setAttribute("result",arr);
            request.getRequestDispatcher("/FindView").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String type = request.getParameter("type");
        if("gotoFindView".equals(type)) {
            request.getRequestDispatcher("/FindView").forward(request,response);
        }else if("out".equals(type)) {
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for (Cookie c: cookies) {
                    if("password".equals(c.getName())) {
                        c.setMaxAge(0);
                        response.addCookie(c);
                    }
                }
                HttpSession session = request.getSession();
                session.invalidate();
                request.setAttribute("msg","安全退出成功！");
                request.getRequestDispatcher("/Login").forward(request,response);
            }

        }
    }
}

package com.eachen.controller;

import com.eachen.domain.User;
import com.eachen.servive.UserSevive;
import javafx.beans.property.Property;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

@WebServlet(name = "LoginCl", urlPatterns = "/LoginCl")
public class LoginCl extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        String id = request.getParameter("id");
        String password = request.getParameter("password");
//        int visitnum = 0;
//        try {
//            visitnum = (int) this.getServletContext().getAttribute("visitnum");
//        }catch (NullPointerException e ) {
//            System.out.println("为空");
//            visitnum = 0;
//        }

        if("".equals(id) || "".equals(password)) {
            request.setAttribute("msg", "用户名或密码不能为空");
            request.getRequestDispatcher("/Login").forward(request, response);
        }
        String keep = request.getParameter("iskeepinfo");
        if("on".equals(keep)) {
            Cookie idCookie = new Cookie("id",id);
            Cookie pwdCookie = new Cookie("password",password);
            idCookie.setMaxAge(7 * 24 * 3600);
            pwdCookie.setMaxAge(7 * 24 * 3600);
            response.addCookie(idCookie);
            response.addCookie(pwdCookie);
        }

        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setPassowrd(password);

        UserSevive userSevive = new UserSevive();
        Map<String,String> isuser = userSevive.checkUser(user);
        if ("true".equals(isuser.get("result"))) {
            String visitnum = (String) this.getServletContext().getAttribute("visitnum");
            if(visitnum == null) {
                this.getServletContext().setAttribute("visitnum","1");
            }else {
                this.getServletContext().setAttribute("visitnum",(Integer.parseInt(visitnum) + 1 + ""));
            }
            user.setUsername((String) isuser.get("username"));
            HttpSession session = request.getSession();
            session.setAttribute("loginUser",user);
            response.sendRedirect("/ManagerLogin/mainFrame");
//            request.getRequestDispatcher("/mainFrame").forward(request, response);
        } else {
            request.setAttribute("msg", "用户名或密码错误");
            request.getRequestDispatcher("/Login").forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
//        String filepath = this.getServletContext().getRealPath("dbinfo.properties");
//        FileWriter fw = new FileWriter();
        System.out.println("迪欧用我了");
        Properties property = null;
//        String filepath = this.getServletContext().getRealPath("dbinfo.properties");
        String filepath = LoginCl.class.getClassLoader().getResource("visit.properties").getPath();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filepath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        InputStream fis = LoginCl.class.getClassLoader().getResourceAsStream("dbinfo.properties");
        try {
            property = new Properties();
//            property.store(fos,"update");
//            String visitnum = property.getProperty("visitnum");
            String num = (String) this.getServletContext().getAttribute("visitnum");
            if (num == null) {
                property.setProperty("visitnum","0");
                property.store(fos,"update");
            }else {
                property.setProperty("visitnum",num);
                property.store(fos,"update");
            }
//            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void init() throws ServletException {
//        super.init(config);
        /*String filepath = this.getServletContext().getRealPath("dbinfo.properties");
        try {
            FileInputStream fis = new FileInputStream(filepath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] arr = new byte[1024*8];
            int len;
            while((len = bis.read(arr)) != -1) {
                bis.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        System.out.println("初始化我");
        Properties property = null;
        InputStream fis = null;
        fis = LoginCl.class.getClassLoader().getResourceAsStream("visit.properties");
//        fis = this.getServletContext().getResourceAsStream("dbinfo.properties");
        if(fis != null) {
            try {
                property = new Properties();
                property.load(fis);
                String visitnum = property.getProperty("visitnum");
//            String num = (String) this.getServletContext().getAttribute("visitnum");
                if( "0".equals(visitnum)) {
                    this.getServletContext().setAttribute("visitnum","1");
                }else {
                    this.getServletContext().setAttribute("visitnum",visitnum);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }
}

package com.mizore.servlet.admin;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/backend/home/*")
public class HomeManageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
//        获取请求路径
        String uri = request.getRequestURI();

//        截取请求路径
        uri = uri.replace("/backend/home", "");

//        返回首页管理页面
        if (uri.isEmpty()){
            request.getRequestDispatcher("/WEB-INF/views/admin/home_manage.jsp").forward(request,response);
        }




    }

}

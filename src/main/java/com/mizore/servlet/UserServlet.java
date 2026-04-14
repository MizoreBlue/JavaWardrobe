package com.mizore.servlet;

import com.mizore.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/product/*")
public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        根据请求路径区分操作类型
        // 获取操作类型，默认为列表
        String uri = req.getRequestURI();
        if (uri.contains("/login")) {

        }
        // 这里可以扩展 details, search 等操作
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
//        处理登录请求
        if (uri.contains("/login")) {

        }
    }
}
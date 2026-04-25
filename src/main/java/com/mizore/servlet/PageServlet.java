package com.mizore.servlet;

import com.mizore.entity.Clothes;
import com.mizore.service.ClothesService;
import com.mizore.service.impl.ClothesServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

// 统一管理页面跳转，不负责业务逻辑
@WebServlet(urlPatterns = {"/"})
public class PageServlet extends HttpServlet {

    private ClothesService clothesService = new ClothesServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String uri = req.getRequestURI();

        // 处理首页请求
        if (uri.equals("/") || uri.equals(req.getContextPath() + "/")) {
            // 1. 调用 Service 层获取数据
            List<Clothes> clothesList = clothesService.getClothesList();

            // 2. 将数据存入 request 作用域，key 为 "clothesList"
            req.setAttribute("clothesList", clothesList);

            // 3. 转发到 JSP 页面进行渲染
             req.getRequestDispatcher("/WEB-INF/views/client/index.jsp").forward(req, resp);
        }
    }
}
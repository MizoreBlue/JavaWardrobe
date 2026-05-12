package com.mizore.servlet.admin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizore.entity.Clothes;
import com.mizore.entity.User;
import com.mizore.service.ClothesService;
import com.mizore.service.UserService;
import com.mizore.service.impl.ClothesServiceImpl;
import com.mizore.service.impl.UserServiceImpl;
import com.mizore.utils.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/backend/clothes/*")
@MultipartConfig
public class ClothesManageServlet extends HttpServlet {

    private ClothesService clothesService = new ClothesServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        uri处理
        String uri = request.getRequestURI();
        uri = uri.replace("/backend/clothes/", "");

        if (uri.contains("list")) {
//            展示服装列表
            List<Clothes> clothesList = clothesService.getClothesList();
            request.setAttribute("clothesList",clothesList);
            request.getRequestDispatcher("/WEB-INF/views/admin/clothes_manage.jsp").forward(request,response);
        }
    }
}
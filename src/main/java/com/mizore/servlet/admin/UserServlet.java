package com.mizore.servlet.admin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizore.entity.User;
import com.mizore.service.UserService;
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

@WebServlet(urlPatterns = "/backend/user/*")
@MultipartConfig
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    // Jackson 对象映射器，用于将对象转为 JSON 字符串
    private ObjectMapper objectMapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        uri处理
        String uri = request.getRequestURI();

        uri = uri.replace("/backend/user/", "/");

//        展示用户管理页面
        if (uri.contains("list")) {
            List<User> users = userService.getAllUser();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/WEB-INF/views/admin/user_manage.jsp").forward(request,response);
        }

//        展示添加用户表单
        if (uri.contains("add")) {

            request.getRequestDispatcher("/WEB-INF/views/admin/user_add.jsp").forward(request,response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        uri = uri.replace("/backend/user/", "/");

        //        设置响应体编码
        response.setContentType("application/json;charset=UTF-8");

//        添加一个用户
        if (uri.contains("add")) {
            User user = User.builder()
                    .username(request.getParameter("username"))
                    .password(request.getParameter("password"))
                    .phone(request.getParameter("phone"))
                    .role(Long.valueOf(request.getParameter("role")))
                    .build();

            boolean flag = userService.register(user);

            Result<String> result;
            if (flag) {
                result = Result.success("添加用户成功");
            }else {
                result = Result.error("添加用户失败");
            }

            //            将对象转为字符串并写入响应流
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().write(json);
        }
    }

}

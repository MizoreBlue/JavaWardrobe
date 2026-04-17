package com.mizore.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mizore.entity.User;
import com.mizore.service.UserService;
import com.mizore.service.impl.UserServiceImpl;
import com.mizore.utils.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    // Jackson 对象映射器，用于将对象转为 JSON 字符串
    private ObjectMapper objectMapper = new ObjectMapper();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();

        //        响应注册页面
        if (uri.contains("/login")) {
            req.getRequestDispatcher("/WEB-INF/views/client/login.jsp").forward(req, resp);
        }

        //        响应注册页面
        if (uri.contains("/register")) {
            req.getRequestDispatcher("/WEB-INF/views/client/register.jsp").forward(req, resp);
        }

        if (uri.contains("/logout")) {
            // 销毁 Session
            req.getSession().invalidate();
            // 重定向回首页
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

//        设置响应体编码
        resp.setContentType("application/json;charset=UTF-8");


        String uri = req.getRequestURI();


        /*
          用户登录
         */
        if (uri.contains("/login")) {
            User user = User.builder()
                    .username(req.getParameter("username"))
                    .password(req.getParameter("password"))
                    .build();
//            参数封装
//            调用service 层
            boolean result = userService.login(user);
            if (result) {
//                比对成功返回主页，将用户信息存贮到session
                user.setPassword(null);
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/");
            } else {
//                登录失败 提示密码错误
                // 注意：这里存入 session 是为了在重定向后还能获取到刚才输入的用户名，用于回显
                req.getSession().setAttribute("loginError", "用户名或密码错误");
                // 浏览器会发起一个新的 GET 请求，地址栏变为 /user/login
                resp.sendRedirect(req.getContextPath() + "/user/login");
            }
        }


        /*
          用户注册
         */
        if (uri.contains("/register")) {

//            构建实体对象
            User user = User.builder()
                    .username(req.getParameter("username"))
                    .password(req.getParameter("password"))
                    .sex(req.getParameter("sex"))
                    .phone(req.getParameter("phone"))
                    .email(req.getParameter("email"))
                    .avatar(req.getParameter("avatar"))
                    .createTime(LocalDateTime.now())
                    .build();

//            调用service 层
            boolean isSuccess = userService.register(user);


            Result<String> result;
            if (isSuccess) {
                result = Result.success("注册成功，请登录");
            }else {
               result = Result.error("注册失败，用户名可能已存在");
            }

//            将对象转为字符串并写入响应流
            String json = objectMapper.writeValueAsString(result);
            resp.getWriter().write(json);
        }
    }
}
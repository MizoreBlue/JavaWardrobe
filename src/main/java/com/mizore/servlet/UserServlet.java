package com.mizore.servlet;

import com.mizore.entity.User;
import com.mizore.service.UserService;
import com.mizore.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uri = req.getRequestURI();

        //        响应注册页面
        if (uri.contains("/login")) {
            req.getRequestDispatcher("/WEB-INF/views/client/login.jsp").forward(req, resp);
        }

        //        响应注册页面
        if (uri.contains("register")) {
            req.getRequestDispatcher("/WEB-INF/views/client/register.jsp").forward(req, resp);
        }

        if (uri.contains("/logout")) {
            // 销毁 Session
            req.getSession().invalidate();
            // 重定向回首页
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String uri = req.getRequestURI();
//        处理登录请求
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
            }else {
//                登录失败 提示密码错误
                // 注意：这里存入 session 是为了在重定向后还能获取到刚才输入的用户名，用于回显
                req.getSession().setAttribute("loginError", "用户名或密码错误");
                // 浏览器会发起一个新的 GET 请求，地址栏变为 /user/login
                resp.sendRedirect(req.getContextPath() + "/user/login");
            }
        }
    }
}
package com.mizore.filter.user;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

// 拦截所有请求
@WebFilter("/*")
public class UserLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();



        // 1. 定义不需要登录就能访问的“白名单”路径
        // 根据你的需求：登录页、退出登录、首页、以及静态资源（双重保险）
        if (uri.contains("/login") ||
                uri.contains("/logout") ||
                uri.equals("/home") ||
                uri.contains("/register") ||
                uri.contains("/backend") ||
                uri.equals("/")) {
            // 白名单路径直接放行
            chain.doFilter(request, response);
            return;
        }

        // 2. 检查用户是否已登录（从 Session 中获取用户信息）
        HttpSession session = httpRequest.getSession();

        Object loginUser = session.getAttribute("user");

        if (loginUser != null) {
            // 已登录，放行请求
            chain.doFilter(request, response);
        } else {
            // 未登录，重定向到登录页面
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/user/login");
        }
    }
}
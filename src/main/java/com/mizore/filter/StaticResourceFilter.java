package com.mizore.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class StaticResourceFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

//        基于 HTTP
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String uri = httpRequest.getRequestURI();

//        处理POST 请求乱码
        httpRequest.setCharacterEncoding("UTF-8");


        // 1. 如果是静态资源
        if (isStaticResource(uri)) {
            // 设置缓存策略（开发阶段建议禁用缓存，方便调试）
            httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

            RequestDispatcher rd = request.getServletContext().getNamedDispatcher("default");
            rd.forward(httpRequest, httpResponse);
            return;
        }

        // 【关键修改】：直接放行，交给后面的 Servlet 处理
        chain.doFilter(httpRequest, httpResponse);
    }

    private boolean isStaticResource(String uri) {
        return uri.endsWith(".css") ||
                uri.endsWith(".js") ||
                uri.endsWith(".png") ||
                uri.endsWith(".jpg") ||
                uri.endsWith(".jpeg") ||
                uri.endsWith(".gif") ||
                uri.endsWith(".ico") ||
                uri.endsWith(".svg") ||
                uri.endsWith(".woff") ||
                uri.endsWith(".ttf");
    }
}
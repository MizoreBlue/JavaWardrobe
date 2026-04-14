
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="jakarta.servlet.http.Cookie" %>

<div>
    <div>
        <i class="bi bi-speedometer2"></i>
    </div>
    <h2>欢迎使用网上衣橱管理系统</h2>

    <%-- Cookie 记录上次访问时间 --%>
    <%
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        boolean flag = false;

        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("lastTime".equals(name)) {
                    flag = true;
                    String value = URLDecoder.decode(cookie.getValue(), "utf-8");
                    out.println("<h4 style='color:#2E86C1'>欢迎回来，您上次访问时间为：" + value + "</h4>");

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                    String nowTime = sdf.format(date);
                    nowTime = URLEncoder.encode(nowTime, "utf-8");
                    cookie.setValue(nowTime);
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        if (cookies == null || cookies.length == 0 || !flag) {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            String nowTime = sdf.format(date);
            nowTime = URLEncoder.encode(nowTime, "utf-8");
            Cookie cookie = new Cookie("lastTime", nowTime);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            out.println("<h4 style='color:#e63946'>您好，欢迎您首次访问！</h4>");
        }
    %>
</div>
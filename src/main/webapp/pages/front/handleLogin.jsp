<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
    String username = request.getParameter("username");
    String pwd = request.getParameter("pwd");
    String msg = "账号或者密码错误，登录失败";
//    将中文进行编码ASCII 浏览器无法解析非ASCII
    String encodeMsg = URLEncoder.encode(msg, "UTF-8");

    boolean loginSuccess = "zhangsan".equals(username) && "123456".equals(pwd);

    if (loginSuccess) {
        // 登录成功：存session，跳首页
        session.setAttribute("loginUser", username);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    } else {
        // 登录失败：跳回登录页
        response.sendRedirect(request.getContextPath() + "/pages/front/login.jsp?msg="+encodeMsg);
    }
%>
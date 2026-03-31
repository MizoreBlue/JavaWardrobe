<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // 从session 获取登录用户
    String loginUser = (String) session.getAttribute("loginUser");
%>
<link type="text/css" rel="stylesheet" href="../../css/header.css">


<!-- 1. 导航栏模块 -->
<div class="navbar">
    <div class="nav-left">
        <span class="logo">网上衣橱</span>
        <a href="<%=request.getContextPath()%>/index.jsp">首页</a>
        <a href="#">服装类别</a>
        <a href="#">服装风格</a>
        <a href="#">购物车</a>
        <a href="#">我的订单</a>
        <a href="#">个人中心</a>

        <%-- 未登录：显示 注册、登录 --%>
        <%
            if (loginUser == null) {
        %>
        <a href="<%=request.getContextPath()%>/pages/front/register.jsp">注册</a>
        <a href="<%=request.getContextPath()%>/pages/front/login.jsp">登录</a>
        <%
            }
        %>
    </div>

    <!-- 右侧：已登录才显示 欢迎信息 + 退出登录 -->
    <div class="nav-right">
        <%
            if (loginUser != null) {
                System.out.println(loginUser);
        %>
        <%=loginUser%>，欢迎您！
        &nbsp;&nbsp;
        <a href="<%=request.getContextPath()%>/pages/front/login.jsp">退出登录</a>
        <%
            }
        %>
    </div>
</div>
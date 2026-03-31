
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
    <link type="text/css" rel="stylesheet" href="../../css/backIndex.css">
    <style>

    </style>
</head>
<body>
<!-- 顶部导航栏 -->
<nav class="top-navbar">
    <h5>网上衣橱 - 后台管理</h5>
    <div class="admin-info">
        <div class="admin-avatar">
            <i class="bi bi-person"></i>
        </div>
        <span>admin</span>
    </div>
</nav>

<!-- 侧边栏导航 -->
<div class="sidebar">
    <ul class="nav flex-column">
        <li class="nav-item">
            <%
                String p = request.getParameter("page");
                if (p == null || "backHome".equals(p)) {
            %>
            <a class="nav-link active" href="backIndex.jsp?page=backHome">
                <i class="bi bi-house-door"></i>
                首页
            </a>
            <%
                } else {
            %>
            <a class="nav-link" href="backIndex.jsp?page=backHome">
                <i class="bi bi-house-door"></i>
                首页
            </a>
            <%
                }
            %>
        </li>
        <li class="nav-item">
            <a class="nav-link"
               href="backIndex.jsp?page=clothingManage">
                <i class="bi bi-box-seam"></i>
                服装管理
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link"
               href="backIndex.jsp?page=orderManage">
                <i class="bi bi-cart-check"></i>
                订单管理
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link"
               href="backIndex.jsp?page=userManage">
                <i class="bi bi-people"></i>
                用户管理
            </a>
        </li>
    </ul>
</div>

<!-- 主内容区 -->
<div class="main-content">
    <div >
        <%
            if (p == null || "backHome".equals(p)) {
        %>
        <jsp:include page="backHome.jsp" />
        <%
            } else if ("clothingManage".equals(p)) {
        %>
        <jsp:include page="clothingManage.jsp" />
        <%
            } else if ("orderManage".equals(p)) {
        %>
        <jsp:include page="orderManage.jsp" />
        <%
            } else if ("userManage".equals(p)) {
        %>
        <jsp:include page="userManage.jsp" />
        <%
            }
        %>
    </div>
</div>
</body>
</html>

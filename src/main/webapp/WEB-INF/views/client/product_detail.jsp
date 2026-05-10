<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="com.mizore.entity.Clothes" %> <%-- 记得导入你的实体类包名 --%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>商品详情</title>
    <!-- 引入CSS，使用 EL 表达式获取项目路径 -->
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/product_detail.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">
</head>
<body>

<!-- 引入头部 -->
<%@include file="../common/header.jsp"%>

<%
    // 1. 从后端 Request 域中获取 Clothes 对象
    Clothes clothes = (Clothes) request.getAttribute("clothes");

    // 2. 简单的防御性编程：如果没查到商品，给一个默认空对象或提示
    if (clothes == null) {
        clothes = new Clothes();
        // 实际开发中这里可以重定向到 404 页或列表页
        // response.sendRedirect(request.getContextPath() + "/backend/clothes/list");
    }
%>

<div class="detail-container">
    <!-- 左侧商品图片展示区 -->
    <div class="detail-image">
        <!-- 使用 EL 表达式或 JSP 脚本输出图片路径 -->
        <img src="${pageContext.request.contextPath}/assets/images/<%= clothes.getImage() %>" alt="<%= clothes.getName() %>">
    </div>

    <!-- 右侧商品信息展示区 -->
    <div class="detail-info">
        <!-- 商品名称 -->
        <h2><%= clothes.getName() %></h2>

        <!-- 商品风格 -->
        <p>风格：<%= clothes.getStyle() %></p>

        <!-- 类别ID：通常这里显示的是类别名称，需要关联查询，这里暂显ID -->
        <p>类别ID：<%= clothes.getCategoryId() %></p>

        <!-- 价格 -->
        <p class="price">¥<%= clothes.getPrice() %></p>

        <!-- 库存 -->
        <p class="stock">库存：<%= clothes.getStock() %> 件</p>

        <!-- 加入购物车表单 -->
        <!-- 注意：action 路径建议写全，或者确保相对路径正确 -->
        <form id="cartForm" action="${pageContext.request.contextPath}/cart/add" method="post">

            <!-- 核心：只提交商品 ID，其他信息（价格、名称）由后端根据 ID 确认，防止篡改 -->
            <input type="hidden" name="id" value="<%= clothes.getId() %>">

            <!-- 尺码选择 (这是用户交互数据，必须提交) -->
            <div class="size-selector">
                <span>可选尺码：</span>
                <label class="size-label">
                    <input type="radio" name="size" value="S" required checked>
                    <span>S</span>
                </label>
                <label class="size-label">
                    <input type="radio" name="size" value="M" required>
                    <span>M</span>
                </label>
                <label class="size-label">
                    <input type="radio" name="size" value="L" required>
                    <span>L</span>
                </label>
            </div>

            <button type="submit" class="add-cart-btn">加入购物车</button>
            <a href="javascript:history.back()" class="back-btn">返回列表</a>
        </form>
    </div>
</div>

<!-- 引入底部 -->
<%@include file="../common/footer.jsp"%>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>商品详情</title>
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/product_detail.css">
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">

</head>
<body>

<!-- 引入头部 -->
<%@include file="../common/header.jsp"%>

<!-- 2. 商品详情模块 -->
<%
    // 从 request 中取出所有商品参数
    String productName = request.getParameter("name");
    String productImage = request.getParameter("image");
    String productStyle = request.getParameter("style");
    String productCategory = request.getParameter("category");
    String productPrice = request.getParameter("price");
%>

<div class="detail-container">
    <!-- 左侧商品图片展示区 -->
    <div class="detail-image">
        <img src="<%=request.getContextPath()%>/images/<%=productImage%>" alt="<%=productName%>">
    </div>

    <!-- 右侧商品信息展示区 -->
    <div class="detail-info">
        <h2><%= productName != null ? productName : "商品详情" %></h2>
        <p>风格：<%= productStyle != null ? productStyle : "未知" %></p>
        <p>类别：<%= productCategory != null ? productCategory : "上衣" %></p>
        <p class="price">¥<%= productPrice != null ? productPrice : "0" %></p>

        <form id="cartForm" action="addCart.jsp" method="post">
            <!-- 隐藏域：自动提交商品信息 -->
            <input type="hidden" name="productName" value="<%=productName%>">
            <input type="hidden" name="productImage" value="<%=productImage%>">
            <input type="hidden" name="productStyle" value="<%=productStyle%>">
            <input type="hidden" name="productCategory" value="<%=productCategory%>">
            <input type="hidden" name="productPrice" value="<%=productPrice%>">

            <!-- 尺码选择 -->
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
            <a href="javascript:history.back()" class="back-btn">返回首页</a>
        </form>
    </div>
</div>

<!-- 引入底部 -->
<%@include file="../common/footer.jsp"%>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>网上衣橱</title>
  <link type="text/css" rel="stylesheet" href="css/index.css">
</head>
<body>
<!-- 1. 导航栏模块 -->
<%@include file="pages/front/common/header.jsp"%>

<!-- 2. 页面头部 -->
<div class="page-header">
  <h2>本季新品</h2>
  <div class="search-bar">
    <input type="text" placeholder="搜索商品...">
    <button>搜索</button>
  </div>
</div>

<!-- 3. 商品展示区 -->
<div class="product-container">
  <!-- 商品卡片1 -->
  <div class="product-card">
    <img src="images/1.jpeg" alt="商品图片">
    <p class="product-name">简约短袖T恤</p>
    <p class="product-style">休闲风</p>
    <p class="product-price">¥99</p>
    <%--    <button class="detail-btn">查看详情</button>--%>
    <a class="detail-btn" href="pages/front/productDetail.jsp?name=简约短袖T恤&image=1.jpeg&style=休闲风&price=99">查看详情</a>
  </div>
  <!-- 商品卡片2-6：复制上面的卡片，修改文字即可 -->
  <div class="product-card">
    <img src="images/2.jpeg" alt="商品图片">
    <p class="product-name">高腰牛仔裤</p>
    <p class="product-style">复古风</p>
    <p class="product-price">¥199</p>
    <button class="detail-btn">查看详情</button>
  </div>
  <div class="product-card">
    <img src="images/3.jpeg" alt="商品图片">
    <p class="product-name">宽松卫衣</p>
    <p class="product-style">运动风</p>
    <p class="product-price">¥159</p>
    <button class="detail-btn">查看详情</button>
  </div>
  <div class="product-card">
    <img src="images/4.jpeg" alt="商品图片">
    <p class="product-name">西装外套</p>
    <p class="product-style">通勤风</p>
    <p class="product-price">¥299</p>
    <button class="detail-btn">查看详情</button>
  </div>
  <div class="product-card">
    <img src="images/5.jpeg" alt="商品图片">
    <p class="product-name">碎花连衣裙</p>
    <p class="product-style">甜美风</p>
    <p class="product-price">¥229</p>
    <button class="detail-btn">查看详情</button>
  </div>
  <div class="product-card">
    <img src="images/6.jpeg" alt="商品图片">
    <p class="product-name">工装短裤</p>
    <p class="product-style">街头风</p>
    <p class="product-price">¥119</p>
    <button class="detail-btn">查看详情</button>
  </div>
</div>

<!-- 4. 页脚 -->
<%@include file="pages/front/common/footer.jsp"%>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: love_
  Date: 2026/3/3
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>网上衣橱</title>
  <!-- 后续会在这里加CSS样式 -->

  <style>
    /* 全局重置：消除浏览器默认样式 */
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: "微软雅黑", Arial, sans-serif;
    }
    a {
      text-decoration: none; /* 去掉链接下划线 */
    }

    /* 1. 导航栏样式 */
    .navbar {
      background-color: #2E86C1; /* 主色调：蓝色 */
      color: white;
      padding: 15px 20px;
      display: flex; /* 横向排列 */
      justify-content: space-between; /* 左右分开 */
      align-items: center; /* 垂直居中 */
    }
    .nav-left .logo {
      font-size: 20px;
      font-weight: bold;
      margin-right: 20px;
    }
    .nav-left a {
      color: white;
      margin: 0 10px;
    }

    /* 2. 页面头部样式 */
    .page-header {
      padding: 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .search-bar input {
      padding: 6px 10px;
      width: 200px;
      border: 1px solid #ddd;
      border-radius: 4px 0 0 4px; /* 左圆角 */
    }
    .search-bar button {
      padding: 6px 15px;
      background-color: #2E86C1;
      color: white;
      border: none;
      border-radius: 0 4px 4px 0; /* 右圆角 */
      cursor: pointer; /* 鼠标变手型 */
    }

    /* 3. 商品展示区样式 */
    .product-container {
      padding: 0 20px 20px;
      display: flex; /* 卡片横向排列 */
      flex-wrap: wrap; /* 超出换行 */
      gap: 20px; /* 卡片之间的间距 */
    }
    .product-card {
      width: 180px;
      border: 1px solid #eee;
      border-radius: 8px;
      padding: 15px;
      text-align: center; /* 内容居中 */
    }
    .product-card img {
      width: 100%; /* 图片适配卡片宽度 */
      border-radius: 4px;
      margin-bottom: 10px;
    }
    .product-style {
      color: #999;
      font-size: 12px;
      margin: 5px 0;
    }
    .product-price {
      color: #e63946;
      font-weight: bold;
      margin: 5px 0;
    }
    .detail-btn {
      background-color: #2E86C1;
      color: white;
      border: none;
      padding: 6px 12px;
      border-radius: 4px;
      cursor: pointer;
      margin-top: 5px;
    }
    .detail-btn:hover {
      background-color: #1976D2; /* 鼠标悬停加深蓝色 */
    }

    /* 4. 页脚样式 */
    .footer {
      background-color: #f5f5f5;
      text-align: center;
      padding: 15px;
      font-size: 12px;
      color: #666;
      margin-top: 20px;
    }
  </style>
</head>
<body>
<!-- 1. 导航栏模块 -->
<div class="navbar">
  <div class="nav-left">
    <span class="logo">网上衣橱</span>
    <a href="http://localhost:8080/web1/index.jsp">首页</a>
    <a href="#">服装类别</a>
    <a href="#">服装风格</a>
    <a href="#">购物车</a>
    <a href="#">我的订单</a>
    <a href="#">个人中心</a>
    <a href="http://localhost:8080/web1/pages/front/register.html">注册</a>
    <a href="http://localhost:8080/web1/pages/front/login.html">登录</a>
  </div>
  <div class="nav-right">**，欢迎您！</div>
</div>

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
    <button class="detail-btn">查看详情</button>
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
<div class="footer">
  <p>© 2026 网上衣橱 版权所有 | 联系电话：520 | 地址：广东技术师范大学</p>
</div>
</body>
</html>

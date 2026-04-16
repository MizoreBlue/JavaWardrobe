<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 1. 引入 JSTL 核心标签库 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="UTF-8">
  <title>网上衣橱</title>
  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/index.css">
</head>
<body>
<!-- 1. 导航栏模块 -->
<%@include file="../common/header.jsp"%>

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
  <%-- 2. 使用 JSTL 的 forEach 标签遍历集合 --%>
  <c:forEach items="${clothesList}" var="clothes">
    <div class="product-card">
        <%-- 3. 使用 EL 表达式 ${} 获取对象属性 --%>
      <img src="<%=request.getContextPath()%>/assets/images/${clothes.image}" alt="${clothes.name}">
      <p class="product-name">${clothes.name}</p>
      <p class="product-style">${clothes.style}</p>
      <p class="product-price">¥${clothes.price}</p>
      <a class="detail-btn" href="${pageContext.request.contextPath}/clothesDetail?id=${clothes.id}">查看详情</a>
    </div>
  </c:forEach>
</div>

<!-- 4. 页脚 -->
<%@include file="../common/footer.jsp"%>
</body>
</html>
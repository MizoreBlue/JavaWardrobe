<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<style>
    /* 侧边栏容器背景色 */
    .sidebar-container {
        background-color: #2c3e50; /* 深蓝灰色背景 */
        height: 100vh; /* 占满全屏高度 */
        width: 250px; /* 固定宽度 */
        position: fixed; /* 固定定位，防止滚动消失 */
        left: 0;
        top: 0;
        padding-top: 60px; /* 留出顶部导航栏的空间 */
        box-shadow: 2px 0 5px rgba(0,0,0,0.1);
        z-index: 1000;
    }

    /* 列表基础样式 */
    .menu-list {
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .menu-item {
        border-bottom: 1px solid #34495e; /* 分割线颜色 */
    }

    /* 链接样式 */
    .menu-link {
        display: flex;
        align-items: center;
        padding: 15px 20px;
        color: #bdc3c7; /* 浅灰色文字 */
        text-decoration: none;
        font-size: 15px;
        transition: all 0.3s ease;
    }

    /* 图标样式 */
    .menu-icon {
        margin-right: 10px;
        font-size: 18px;
        width: 20px;
        text-align: center;
    }

    /* 悬停效果 */
    .menu-link:hover {
        background-color: #34495e;
        color: #fff;
        padding-left: 25px; /* 向右滑动动画 */
        text-decoration: none;
    }

    /* 选中状态 (如果需要高亮当前页面) */
    .menu-link.active {
        background-color: #1abc9c; /* 绿色高亮 */
        color: #fff;
    }
</style>

<!-- 侧边栏结构 -->
<div class="sidebar-container">
    <ul class="menu-list">
        <!-- 首页 -->
        <li class="menu-item">
            <a href="<%=path%>/backend/home" target="mainFrame" class="menu-link">
                <span class="menu-icon">🏠</span>
                首页
            </a>
        </li>

        <!-- 商品/服装管理 -->
        <li class="menu-item">
            <a href="<%=path%>/backend/clothes/list" target="mainFrame" class="menu-link">
                <span class="menu-icon">👕</span>
                服装管理
            </a>
        </li>

        <!-- 订单管理 -->
        <li class="menu-item">
            <a href="<%=path%>/backend/order/list" target="mainFrame" class="menu-link">
                <span class="menu-icon">📝</span>
                订单管理
            </a>
        </li>

        <!-- 用户管理 -->
        <li class="menu-item">
            <a href="<%=path%>/backend/user/list" target="mainFrame" class="menu-link">
                <span class="menu-icon">👥</span>
                用户管理
            </a>
        </li>
    </ul>
</div>
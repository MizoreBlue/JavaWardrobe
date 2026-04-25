<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>网上衣橱 - 后台管理</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        /* 重置 body 默认 margin，防止出现滚动条或错位 */
        body {
            margin: 0;
            padding: 0;
            overflow-x: hidden; /* 防止横向滚动条 */
        }

        /* 顶部导航栏 */
        .top-navbar {
            height: 60px;
            background-color: #2E86C1;
            color: white;
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 1002; /* 比侧边栏和内容区都高，置顶 */
            display: flex;
            align-items: center;
            padding: 0 20px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        /* 侧边栏容器 */
        .sidebar-container {
            background-color: #2c3e50;
            height: 100vh;
            width: 250px;
            position: fixed; /* 关键：固定定位 */
            left: 0;
            top: 60px; /* 紧接在顶部导航下方 */
            padding-top: 20px;
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
            z-index: 1001; /* 比内容高，但低于顶部导航 */
            overflow-y: auto;
        }

        /* 右侧内容区域（关键修复点） */
        /* 使用 !important 强制覆盖浏览器默认样式 */
        .content-area {
            position: relative; /* 创建新的层叠上下文 */
            margin-left: 250px; /* 为侧边栏留出空间 */
            margin-top: 60px; /* 为顶部导航留出空间 */
            z-index: 1000; /* 正常情况下应该显示在侧边栏之下，但内容需通过 iframe 提升 */
            min-height: calc(100vh - 60px);
            background-color: #f8f9fa;
        }

        /* iframe 样式（最终修复点） */
        /* 必须给 iframe 极高的 z-index，并确保其脱离文档流不影响布局 */
        iframe {
            width: 100%;
            min-height: 800px;
            border: none;
            background: transparent;

            /* 核心修复：强制提升层级，覆盖侧边栏 */
            position: relative;
            z-index: 99999 !important;
            opacity: 1; /* 防止某些浏览器透明度继承导致变暗 */
        }

        /* 菜单样式保持不变 */
        .menu-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        .menu-item {
            border-bottom: 1px solid #34495e;
        }
        .menu-link {
            display: flex;
            align-items: center;
            padding: 15px 20px;
            color: #bdc3c7;
            text-decoration: none;
            font-size: 15px;
            transition: all 0.3s ease;
        }
        .menu-link:hover {
            background-color: #34495e;
            color: #fff;
            padding-left: 25px;
        }
        .menu-link.active {
            background-color: #1abc9c;
            color: #fff;
        }
    </style>
</head>
<body>

<!-- 1. 顶部导航 -->
<nav class="top-navbar">
    <h5 class="mb-0">网上衣橱 - 后台管理</h5>
    <div class="ms-auto">
        <i class="bi bi-person-circle"></i> admin
    </div>
</nav>


<%@ include file="../common/left.jsp" %>


<!-- 3. 右侧内容 -->
<!-- 注意：这里不需要额外的 z-index，靠 iframe 自身的 z-index 来突破 -->
<div class="content-area">
    <iframe name="mainFrame" src="/backend/home"></iframe>
</div>

</body>
</html>
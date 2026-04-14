<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理登录</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="../../css/adminLogin.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="../../js/adminLogin.js"></script>
</head>

<body>
<!-- 顶部标题栏 -->
<header class="bg-white py-3 shadow-sm">
    <div class="container">
        <div class="d-flex align-items-center">
            <h1 class="h3 mb-0 text-dark">网上衣橱 - 后台管理</h1>
        </div>
    </div>
</header>

<!-- 登录表单区域 -->
<main class="container d-flex align-items-center justify-content-center" style="min-height: calc(100vh - 76px);">
    <div class="login-card">
        <h2 class="text-center mb-4">管理员登录</h2>
        <!-- 表单内容 -->
        <form id="loginForm" onsubmit="checkLogin()">
            <div class="mb-3">
                <label for="account" class="form-label form-label-required">账号</label>
                <input type="text" class="form-control" id="account" name="account" placeholder="请输入用户名或手机号" required onblur="checkAccount()">
                <span class="error-msg" id="accountError"></span>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label form-label-required">密码</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" required onblur="checkPassword()">
                <span class="error-msg" id="passwordError"></span>
            </div>
            <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                <button type="submit" class="btn btn-primary px-4">登录</button>
                <button type="reset" class="btn btn-primary px-4">重置</button>
            </div>
        </form>
    </div>
</main>
</body>
</html>

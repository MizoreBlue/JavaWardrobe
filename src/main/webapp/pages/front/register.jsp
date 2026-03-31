<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link type="text/css" rel="stylesheet" href="../../css/registerForm.css">
    <script src="../../js/register.js" type="text/javascript"></script>
</head>
<body>
<!-- 1. 导航栏模块 -->
<%@include file="common/header.jsp"%>
<!-- 2. 注册表单区域 -->
<div class="register-container">
    <h2 class="register-title">用户注册</h2>
    <form id="registerForm" onsubmit="check()">
        <!-- 用户名 - 必填 -->
        <div class="form-row">
            <label><span class="required">*</span>用户名</label>
            <input type="text" name="username" placeholder="请输入用户名" value="" id="username" onblur="checkUsername();">
            <span class="icon-placeholder">●</span> <!-- 图标占位符 -->
            <span class="error-msg" id="usernameError"></span>
        </div>
        <!-- 密码 - 必填，密码类型 -->
        <div class="form-row">
            <label><span class="required">*</span>密码</label>
            <input type="password" name="password" placeholder="请输入密码" value="" id="password" onblur="checkPassword();">
            <span class="icon-placeholder">●</span> <!-- 图标占位符 -->
            <span class="error-msg" id="passwordError"></span>
        </div>
        <!-- 手机号 - 必填 -->
        <div class="form-row">
            <label><span class="required">*</span>手机号</label>
            <input type="tel" name="phone" placeholder="请输入手机号" value=""  id="phone" onblur="checkPhone();">
            <span class="icon-placeholder">●</span> <!-- 图标占位符 -->
            <span class="error-msg" id="phoneError"></span>
        </div>
        <!-- 地址 - 非必填 -->
        <div class="form-row">
            <label>地址</label>
            <input type="text" name="address" placeholder="请输入地址（选填）" value="" id="address">
            <span class="icon-placeholder">●</span> <!-- 图标占位符 -->
        </div>
        <!-- 按钮组 -->
        <div class="btn-group">
            <button type="submit" class="btn btn-register">注册</button>
            <button type="reset" class="btn btn-reset">重置</button>
        </div>
    </form>
</div>
<%@include file="common/footer.jsp"%>
</body>
</html>

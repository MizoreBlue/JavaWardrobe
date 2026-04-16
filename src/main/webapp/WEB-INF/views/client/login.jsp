<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>用户登录</title>
  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/common.css">
  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/login_form.css">

  <script src="<%=request.getContextPath()%>/assets/js/login.js" type="text/javascript"></script>

</head>
<body>
<%@include file="../common/header.jsp"%>

<div class="login-container">
  <h2>用户登录</h2>

  <!-- ====================== -->
  <!-- 错误提示区域（已加好） -->
  <!-- ====================== -->
  <%
    String loginError = (String) session.getAttribute("loginError");
    if (loginError != null && !loginError.isEmpty()) {
  %>
  <div style="color:red; text-align:center;">
    <%=loginError%>
  </div>
  <%
    }
  %>

  <form id="loginForm" onsubmit="checkLogin()" action="" method="post">
    <div class="form-group">
      <label for="username">用户名/电话</label>
      <input type="text" id="username" name="username" placeholder="请输入用户名/电话" required>
    </div>
    <div class="form-group">
      <label for="pwd">密码</label>
      <input type="password" id="pwd" name="password" placeholder="请输入密码" required>
    </div>

    <div class="btn-group">
      <button type="submit" class="btn btn-login">登录</button>
      <button type="reset" class="btn btn-reset">重置</button>
    </div>
  </form>
</div>

<%@include file="../common/footer.jsp"%>
</body>
</html>
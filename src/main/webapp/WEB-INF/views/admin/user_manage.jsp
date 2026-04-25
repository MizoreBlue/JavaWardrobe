<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 1. 必须引入 Bootstrap CSS，否则样式会丢失 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">
<!-- 页面主容器，增加一点内边距让内容不贴边 -->
<div class="container-fluid p-4">

    <!-- 1. 顶部标题与搜索栏区域 -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <!-- 左侧标题 -->
        <h4 class="text-secondary">
            <i class="bi bi-people"></i> 用户管理
        </h4>

        <!-- 右侧搜索与添加按钮 -->
        <div class="d-flex align-items-center">
            <!-- 搜索表单 -->
            <form class="d-flex me-3" action="<%=request.getContextPath()%>/backend/user/list" method="get">
                <input class="form-control form-control-sm me-2"
                       type="search"
                       name="keyword"
                       placeholder="请输入用户名或手机号"
                       aria-label="Search"
                       style="width: 220px;">
                <button class="btn btn-primary btn-sm" type="submit" style="margin-right: 20px">
                    <i class="bi bi-search"></i> 搜索
                </button>
                <button class="btn btn-success btn-sm"
                        onclick="window.parent.location.href='<%=request.getContextPath()%>/backend/user/add'">
                    <i class="bi bi-plus-lg"></i> 添加用户
                </button>
            </form>


        </div>
    </div>

    <!-- 2. 用户数据表格卡片 -->
    <div class="card shadow-sm border-0">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover table-striped mb-0 align-middle">
                    <thead class="table-light">
                    <tr>
                        <th scope="col" class="text-center" style="width: 5%;">ID</th>
                        <th scope="col" style="width: 15%;">用户名</th>
                        <th scope="col" style="width: 15%;">密码</th> <!-- 图中显示了密码 -->
                        <th scope="col" style="width: 15%;">电话</th>
                        <th scope="col" style="width: 15%;">角色</th>
                        <th scope="col" class="text-center" style="width: 20%;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- 使用 JSTL 遍历后端传来的 users 集合 -->
                    <c:forEach items="${users}" var="user" varStatus="status">
                        <tr>
                            <!-- ID -->
                            <td class="text-center">${user.id}</td>

                            <!-- 用户名 -->
                            <td>${user.username}</td>

                            <!-- 密码 (实际开发中通常不显示，但参考图中有显示) -->
                            <td>${user.password}</td>

                            <!-- 电话 -->
                            <td>${user.phone}</td>


                            <!-- 角色 -->
                            <td>
                                <!-- 简单的逻辑判断：如果是 admin 显示管理员，否则显示普通用户 -->
                                <c:choose>
                                    <c:when test="${user.role == 1}">
                                        <span class="badge bg-primary">管理员</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">普通用户</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- 操作按钮 -->
                            <td class="text-center">
                                <!-- 修改按钮 (蓝色) -->
                                <button class="btn btn-primary btn-sm">
                                    <i class="bi bi-pencil-square"></i> 修改
                                </button>

                                <!-- 删除按钮 (红色) -->
                                <button class="btn btn-danger btn-sm">
                                    <i class="bi bi-trash"></i> 删除
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                    <!-- 如果集合为空时的提示 -->
                    <c:if test="${empty users}">
                        <tr>
                            <td colspan="7" class="text-center text-muted py-4">
                                暂无用户数据
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
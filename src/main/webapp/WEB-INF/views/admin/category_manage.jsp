<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 引入 Bootstrap CSS 和 Icons -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">

<!-- 页面主容器 -->
<div class="container-fluid p-4">

    <!-- 1. 顶部标题与操作栏 -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <!-- 左侧标题 -->
        <h4 class="text-secondary">
            <i class="bi bi-tags"></i> 分类管理
        </h4>

        <!-- 右侧添加按钮 -->
        <div>
            <button class="btn btn-success btn-sm"
                    onclick="window.parent.location.href='<%=request.getContextPath()%>/backend/category/add'">
                <i class="bi bi-plus-lg"></i> 添加分类
            </button>
        </div>
    </div>

    <!-- 2. 分类数据表格卡片 -->
    <div class="card shadow-sm border-0">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover table-striped mb-0 align-middle">
                    <thead class="table-light">
                    <tr>
                        <th scope="col" class="text-center" style="width: 5%;">ID</th>
                        <th scope="col" style="width: 20%;">分类名称</th>
                        <th scope="col" style="width: 10%;">排序</th>
                        <th scope="col" style="width: 10%;">状态</th>
                        <th scope="col" style="width: 15%;">创建时间</th>
                        <th scope="col" style="width: 15%;">更新时间</th>
                        <th scope="col" class="text-center" style="width: 15%;">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <!-- 使用 JSTL 遍历后端传来的 categoryList 集合 -->
                    <c:forEach items="${categoryList}" var="category" varStatus="status">
                        <tr>
                            <!-- ID -->
                            <td class="text-center">${category.id}</td>

                            <!-- 分类名称 -->
                            <td>${category.name}</td>

                            <!-- 排序 -->
                            <td>
                                <span class="badge bg-info">${category.sort}</span>
                            </td>

                            <!-- 状态 (0禁用 1启用) -->
                            <td>
                                <c:choose>
                                    <c:when test="${category.status == 1}">
                                        <span class="badge bg-success">启用</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">禁用</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- 创建时间 -->
                            <td>
                                <fmt:formatDate value="${category.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                            </td>

                            <!-- 更新时间 -->
                            <td>
                                <fmt:formatDate value="${category.updateTime}" pattern="yyyy-MM-dd HH:mm"/>
                                <c:if test="${empty category.updateTime}">--</c:if>
                            </td>

                            <!-- 操作按钮 -->
                            <td class="text-center">
                                <!-- 修改按钮 -->
                                <button class="btn btn-primary btn-sm me-1"
                                        onclick="window.parent.location.href='<%=request.getContextPath()%>/backend/category/edit?id=${category.id}'">
                                    <i class="bi bi-pencil-square"></i> 修改
                                </button>

                                <!-- 删除按钮 -->
                                <button class="btn btn-danger btn-sm"
                                        onclick="if(confirm('确定要删除【${category.name}】吗？')){ window.parent.location.href='<%=request.getContextPath()%>/backend/category/delete?id=${category.id}' }">
                                    <i class="bi bi-trash"></i> 删除
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                    <!-- 如果集合为空时的提示 -->
                    <c:if test="${empty categoryList}">
                        <tr>
                            <td colspan="7" class="text-center text-muted py-4">
                                暂无分类数据
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 引入 Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">

<!-- 页面主容器 -->
<div class="container-fluid p-4">

    <!-- 1. 顶部标题与操作栏 -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <!-- 左侧标题 -->
        <h4 class="text-secondary">
            <i class="bi bi-shirt"></i> 服装管理
        </h4>

        <!-- 右侧搜索与添加按钮 -->
        <div class="d-flex align-items-center">
            <form class="d-flex me-3" action="<%=request.getContextPath()%>/backend/clothes/list" method="get">
                <input class="form-control form-control-sm me-2"
                       type="search"
                       name="keyword"
                       placeholder="请输入服装名称"
                       aria-label="Search"
                       style="width: 220px;">
                <button class="btn btn-primary btn-sm" type="submit" style="margin-right: 20px">
                    <i class="bi bi-search"></i> 搜索
                </button>
                <button class="btn btn-success btn-sm"
                        onclick="window.parent.location.href='<%=request.getContextPath()%>/backend/clothes/add'">
                    <i class="bi bi-plus-lg"></i> 添加服装
                </button>
            </form>
        </div>
    </div>

    <!-- 2. 服装数据表格卡片 -->
    <div class="card shadow-sm border-0">
        <div class="card-body p-0">
            <div class="table-responsive">
                <table class="table table-hover table-striped mb-0 align-middle">
                    <thead class="table-light">
                    <tr>
                        <th scope="col" class="text-center" style="width: 5%;">ID</th>
                        <th scope="col" style="width: 15%;">名称</th>
                        <th scope="col" style="width: 10%;">风格</th>
                        <th scope="col" style="width: 10%;">分类ID</th>
                        <th scope="col" style="width: 10%;">价格</th>
                        <th scope="col" style="width: 10%;">库存</th>
                        <th scope="col" style="width: 10%;">状态</th>
                        <th scope="col" style="width: 15%;">创建时间</th>
                        <th scope="col" class="text-center" style="width: 15%;">操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <!-- 使用 JSTL 遍历后端传来的 clothesList 集合 -->
                    <c:forEach items="${clothesList}" var="clothes" varStatus="status">
                        <tr>
                            <!-- ID -->
                            <td class="text-center">${clothes.id}</td>

                            <!-- 名称 -->
                            <td>${clothes.name}</td>

                            <!-- 风格 -->
                            <td>${clothes.style}</td>

                            <!-- 分类ID -->
                            <td class="text-primary">${clothes.categoryId}</td>

                            <!-- 价格 (使用 JSTL fmt 格式化金额) -->
                            <td class="text-danger fw-bold">
                                <fmt:formatNumber value="${clothes.price}" type="currency" pattern="¥#,##0.00"/>
                            </td>

                            <!-- 库存 -->
                            <td>
                                <c:choose>
                                    <c:when test="${clothes.stock > 0}">
                                        <span class="badge bg-success">${clothes.stock}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">缺货</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- 状态 (假设 1 为上架, 0 为下架) -->
                            <td>
                                <c:choose>
                                    <c:when test="${clothes.status == 1}">
                                        <span class="badge bg-primary">上架</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">下架</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <!-- 创建时间 -->
                            <td>
                                <fmt:formatDate value="${clothes.createTime}" pattern="yyyy-MM-dd HH:mm"/>
                            </td>

                            <!-- 操作按钮 -->
                            <td class="text-center">
                                <!-- 修改按钮 -->
                                <button class="btn btn-primary btn-sm me-1"
                                        onclick="window.parent.location.href='<%=request.getContextPath()%>/backend/clothes/edit?id=${clothes.id}'">
                                    <i class="bi bi-pencil-square"></i> 修改
                                </button>

                                <!-- 删除按钮 -->
                                <button class="btn btn-danger btn-sm"
                                        onclick="if(confirm('确定要删除【${clothes.name}】吗？')){ window.parent.location.href='<%=request.getContextPath()%>/backend/clothes/delete?id=${clothes.id}' }">
                                    <i class="bi bi-trash"></i> 删除
                                </button>
                            </td>
                        </tr>
                    </c:forEach>

                    <!-- 如果集合为空时的提示 -->
                    <c:if test="${empty clothesList}">
                        <tr>
                            <td colspan="9" class="text-center text-muted py-4">
                                暂无服装数据
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
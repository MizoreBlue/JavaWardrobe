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
                                <!-- 修改按钮：触发模态框 -->
                                <button class="btn btn-primary btn-sm me-1"
                                        data-bs-toggle="modal"
                                        data-bs-target="#modifyModal"
                                        onclick="fillModal(${category.id}, '${category.name}', ${category.sort}, ${category.status})">
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

<!-- 修改分类的悬浮窗 Modal -->
<div class="modal fade" id="modifyModal" tabindex="-1" aria-labelledby="modifyModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifyModalLabel">修改分类</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- 隐藏域存储ID -->
                <input type="hidden" id="modalCategoryId" value="">

                <div class="mb-3">
                    <label for="modalCategoryName" class="form-label">分类名称</label>
                    <input type="text" class="form-control" id="modalCategoryName">
                </div>

                <div class="mb-3">
                    <label for="modalCategorySort" class="form-label">排序</label>
                    <input type="number" class="form-control" id="modalCategorySort" value="0">
                </div>

                <div class="mb-3">
                    <label for="modalCategoryStatus" class="form-label">状态</label>
                    <select class="form-select" id="modalCategoryStatus">
                        <option value="1">启用</option>
                        <option value="0">禁用</option>
                    </select>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" onclick="confirmModify()">确认修改</button>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    // 全局变量，用于存储当前操作的分类ID
    let currentCategoryId = null;

    /**
     * 1. 填充模态框数据
     * 这个函数在点击表格行的"修改"按钮时调用
     */
    function fillModal(id, name, sort, status) {
        // 将ID存入全局变量和隐藏域
        currentCategoryId = id;
        document.getElementById('modalCategoryId').value = id;

        // 填入表单数据
        document.getElementById('modalCategoryName').value = name;
        document.getElementById('modalCategorySort').value = sort;

        // 处理下拉框选中状态
        const statusSelect = document.getElementById('modalCategoryStatus');
        statusSelect.value = status;
    }

    /**
     * 2. 确认修改 (触发 Fetch 请求)
     * 这个函数在点击悬浮窗里的"确认修改"按钮时调用
     */
    function confirmModify() {
        // 1. 获取悬浮窗中的最新值
        const id = document.getElementById('modalCategoryId').value;
        const name = document.getElementById('modalCategoryName').value;
        const sort = document.getElementById('modalCategorySort').value;
        const status = document.getElementById('modalCategoryStatus').value;

        // 2. 简单校验
        if (!name.trim()) {
            alert("分类名称不能为空！");
            return;
        }

        // 3. 发起 Fetch 请求
        fetch('<%=request.getContextPath()%>/backend/category/modify', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: id,
                name: name,
                sort: sort,
                status: status
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                // 4. 处理响应结果
                if (data.code === 1) {
                    alert("修改成功！");
                    // 关闭模态框
                    const modal = bootstrap.Modal.getInstance(document.getElementById('modifyModal'));
                    modal.hide();
                    // 刷新页面以显示最新数据
                    location.reload();
                } else {
                    alert("修改失败：" + data.msg);
                }
            })
            .catch(error => {
                console.error('请求出错:', error);
                alert("网络请求失败，请重试。");
            });
    }
</script>
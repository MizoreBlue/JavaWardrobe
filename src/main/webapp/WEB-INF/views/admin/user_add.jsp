<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <!-- 引入 Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- 引入 Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.0/font/bootstrap-icons.css">

    <style>
        /* 页面内边距，与 user_manage.jsp 保持一致 */
        body {
            background-color: #f8f9fa;
            padding: 20px;
        }
        /* 卡片阴影 */
        .card {
            border: none;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        }
        .card-header {
            background-color: #fff;
            border-bottom: 1px solid #f0f0f0;
            padding: 20px;
            border-radius: 8px 8px 0 0 !important;
        }
        .form-label {
            font-weight: 500;
            color: #333;
        }
    </style>


<div class="container-fluid">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card">
                <div class="card-header bg-white d-flex justify-content-between align-items-center">
                    <h5 class="mb-0 text-primary">
                        <i class="bi bi-person-plus"></i> 新增用户
                    </h5>
                    <!-- 返回按钮 -->
                    <button type="button" class="btn btn-outline-secondary btn-sm"
                            onclick="location.href='<%=request.getContextPath()%>/backend/user/list'">
                        <i class="bi bi-arrow-left"></i> 返回列表
                    </button>
                </div>

                <div class="card-body p-4">
                    <form id="addUserForm">
                        <!-- 用户名 -->
                        <div class="mb-3">
                            <label for="username" class="form-label">用户名</label>
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="请输入用户名" required>
                        </div>

                        <!-- 密码 -->
                        <div class="mb-3">
                            <label for="password" class="form-label">密码</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="请输入密码 (至少6位)" required minlength="6">
                        </div>

                        <!-- 电话 -->
                        <div class="mb-3">
                            <label for="phone" class="form-label">电话</label>
                            <input type="text" class="form-control" id="phone" name="phone"
                                   placeholder="请输入手机号" required pattern="[0-9]{11}"
                                   title="请输入11位手机号码">
                        </div>

                        <!-- 角色选择 -->
                        <div class="mb-4">
                            <label for="role" class="form-label">角色</label>
                            <select class="form-select" id="role" name="role">
                                <option value="0">普通用户</option>
                                <option value="1">管理员</option>
                            </select>
                            <div class="form-text">普通用户权限较低，管理员拥有最高权限。</div>
                        </div>

                        <!-- 提交按钮 -->
                        <div class="d-grid">
                            <button type="submit" class="btn btn-success btn-lg">
                                <i class="bi bi-check-circle"></i> 确认添加
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 引入 Bootstrap JS (可选，主要用于组件交互，这里主要用原生JS) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.getElementById('addUserForm').addEventListener('submit', function (e) {
        e.preventDefault(); // 阻止表单默认提交行为

        // 1. 获取表单数据
        const formData = new FormData(this);
        // 如果需要将数据转换为 JSON 发送，可以在这里处理，但通常 form-data 也可以

        // 2. 发送 AJAX 请求
        fetch('<%=request.getContextPath()%>/backend/user/add', {
            method: 'POST',
            body: formData
        })
            .then(response => response.json()) // 解析 JSON 响应
            .then(data => {

                if (data.code === 1) {
                    // 3. 成功逻辑
                    alert(data.message || "添加成功！");

                    // 4. 跳转回列表页
                    // 注意：因为是在 iframe 中，需要操作 parent 窗口或者 target 所在的 frame
                    // 这里的逻辑是让当前 iframe 跳转到列表页
                    const contextPath = "${pageContext.request.contextPath}";
                    window.location.href = contextPath + "/backend/user/list";

                } else {
                    // 5. 失败逻辑
                    alert(data.message || "添加失败，请稍后重试");
                    // 不跳转，停留在当前页面
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert("系统错误，请联系管理员");
            });
    });
</script>

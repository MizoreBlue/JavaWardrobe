// 显示错误
function showError(inputId, errorId, message) {
    const input = document.getElementById(inputId);
    const errorSpan = document.getElementById(errorId);
    if(input) input.className = 'input-error';
    if(errorSpan) errorSpan.textContent = message;
    return false;
}

// 显示正确
function showSuccess(inputId, errorId) {
    const input = document.getElementById(inputId);
    const errorSpan = document.getElementById(errorId);
    if(input) input.className = 'input-success';
    if(errorSpan) errorSpan.textContent = '';
    return true;
}

// 清除提示
function clearError(inputId, errorId) {
    const input = document.getElementById(inputId);
    const errorSpan = document.getElementById(errorId);
    if(input) input.className = '';
    if(errorSpan) errorSpan.textContent = '';
}

// ... (checkUsername, checkPassword, checkPhone 函数保持不变) ...
function checkUsername() {
    const value = document.getElementById('username').value.trim();
    if (value === '') return showError('username', 'usernameError', '用户名不能为空');
    if (value.length < 3 || value.length > 20) return showError('username', 'usernameError', '用户名长度应为 3-20 位');
    return showSuccess('username', 'usernameError');
}

function checkPassword() {
    const value = document.getElementById('password').value;
    if (value === '') return showError('password', 'passwordError', '密码不能为空');
    if (value.length < 6 || value.length > 16) return showError('password', 'passwordError', '密码长度应为 6-16 位');
    return showSuccess('password', 'passwordError');
}

function checkPhone() {
    const value = document.getElementById('phone').value.trim();
    if (value === '') return showError('phone', 'phoneError', '手机号不能为空');
    if (!/^1[3-9]\d{9}$/.test(value)) return showError('phone', 'phoneError', '请输入正确的手机号（11 位数字）');
    return showSuccess('phone', 'phoneError');
}


// 提交时校验
function check(event) {
    // 兼容写法：确保拿到事件对象
    if (!event) event = window.event;

    let ok = checkUsername() && checkPassword() && checkPhone();

    if (!ok) {
        alert('❌ 请修正表单中的错误后再提交！');
        // 阻止默认提交
        event.preventDefault();
        return false;
    }

    // 【关键】阻止表单的默认跳转行为，我们要用 AJAX 提交
    event.preventDefault();

    // 执行 AJAX 提交
    submitRegister();

    // 再次确保返回 false，防止老式浏览器冒泡
    return false;
}

function submitRegister() {
    const form = document.getElementById('registerForm');
    const formData = new URLSearchParams(new FormData(form))

    // 发送 Fetch 请求
    fetch("/user/register"  , { // 注意路径要加上 /user
        method: 'POST',
        headers:{
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: formData.toString()
    })
        .then(response => {
            // 检查响应是否成功
            if (!response.ok) throw new Error("网络响应错误");
            return response.json();
        })
        .then(data => {
            if (data.code === 1) {
                alert(data.data);
                window.location.href = "login";
            } else {
                alert(data.data);
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("系统错误，请查看控制台");
        });
}

// 重置表单
document.getElementById('registerForm').addEventListener('reset', function() {
    setTimeout(() => {
        clearError('username', 'usernameError');
        clearError('password', 'passwordError');
        clearError('phone', 'phoneError');
    }, 10);
});
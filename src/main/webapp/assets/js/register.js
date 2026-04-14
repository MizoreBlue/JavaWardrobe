// 显示错误
function showError(inputId, errorId, message) {
    const input = document.getElementById(inputId);
    const errorSpan = document.getElementById(errorId);
    input.className = 'input-error';
    errorSpan.textContent = message;
    return false;
}

// 显示正确
function showSuccess(inputId, errorId) {
    const input = document.getElementById(inputId);
    const errorSpan = document.getElementById(errorId);
    input.className = 'input-success';
    errorSpan.textContent = '';
    return true;
}

// 清除提示
function clearError(inputId, errorId) {
    const input = document.getElementById(inputId);
    const errorSpan = document.getElementById(errorId);
    input.className = '';
    errorSpan.textContent = '';
}

// 校验用户名（失去焦点时调用）
function checkUsername() {
    const value = document.getElementById('username').value.trim();
    if (value === '') {
        return showError('username', 'usernameError', '用户名不能为空');
    }
    if (value.length < 3 || value.length > 20) {
        return showError('username', 'usernameError', '用户名长度应为 3-20 位');
    }
    return showSuccess('username', 'usernameError');
}

// 校验密码（失去焦点时调用）
function checkPassword() {
    const value = document.getElementById('password').value;
    if (value === '') {
        return showError('password', 'passwordError', '密码不能为空');
    }
    if (value.length < 6 || value.length > 16) {
        return showError('password', 'passwordError', '密码长度应为 6-16 位');
    }
    return showSuccess('password', 'passwordError');
}

// 校验手机号（失去焦点时调用）
function checkPhone() {
    const value = document.getElementById('phone').value.trim();
    if (value === '') {
        return showError('phone', 'phoneError', '手机号不能为空');
    }
    if (!/^1[3-9]\d{9}$/.test(value)) {
        return showError('phone', 'phoneError', '请输入正确的手机号（11 位数字）');
    }
    return showSuccess('phone', 'phoneError');
}

// 提交时校验所有字段
function check() {
    let ok = true;
    ok = checkUsername() && checkPassword() && checkPhone();
    // 检查结果
    if (!ok) {
        alert('❌ 请修正表单中的错误后再提交！');
        return false;
    }

    alert('✅ 校验通过！准备提交...\n用户名：' + document.getElementById('username').value + '\n手机号：' + document.getElementById('phone').value);
    return true;
}

// 重置表单
document.getElementById('registerForm').addEventListener('reset', function() {
    setTimeout(() => {
        clearError('username', 'usernameError');
        clearError('password', 'passwordError');
        clearError('phone', 'phoneError');
    }, 10);
});
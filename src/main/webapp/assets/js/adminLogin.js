// 显示错误
function showError(inputId, errorId, message) {
    const input = document.getElementById(inputId);
    const errorSpan = document.getElementById(errorId);
    input.className = 'form-control input-error';
    errorSpan.textContent = message;
    return false;
}

// 显示正确
function showSuccess(inputId, errorId) {
    const input = document.getElementById(inputId);
    const errorSpan = document.getElementById(errorId);
    input.className = 'form-control input-success';
    errorSpan.textContent = '';
    return true;
}

// 清除提示
function clearError(inputId, errorId) {
    const input = document.getElementById(inputId);
    const errorSpan = document.getElementById(errorId);
    input.className = 'form-control';
    errorSpan.textContent = '';
}

// 校验账号（失去焦点时调用）
function checkAccount() {
    const value = document.getElementById('account').value.trim();
    if (value === '') {
        return showError('account', 'accountError', '账号不能为空');
    }
    if (value.length < 3 || value.length > 20) {
        return showError('account', 'accountError', '账号长度应为 3-20 位');
    }
    return showSuccess('account', 'accountError');
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

// 提交时校验所有字段
function checkLogin() {
    const isAccountValid = checkAccount();
    const isPasswordValid = checkPassword();

    if (!isAccountValid || !isPasswordValid) {
        alert('❌ 请修正表单中的错误后再提交！');
        return false;
    }

    alert('✅ 校验通过！准备提交...\n账号：' + document.getElementById('account').value);
    return true;
}

// 重置表单
window.addEventListener('DOMContentLoaded', function() {
    document.getElementById('loginForm').addEventListener('reset', function() {
        setTimeout(() => {
            clearError('account', 'accountError');
            clearError('password', 'passwordError');
        }, 10);
    });
});
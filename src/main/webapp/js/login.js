function checkLogin(){
    let username = document.getElementById("username").value;
    let pwd = document.getElementById("pwd").value;
    if(username === "" || pwd === ""){
        alert("用户名和密码不能为空！");
        return false;
    }
    return true;
}
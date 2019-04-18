<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 19-4-17
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <div>
        <div>
            用户名：<input type="text" id="username" name="username"><br>
            密码:<input type="text" id="password" name="password">
            <input type="button" id="loginBut" onclick="submitData()" value="登录">
        </div>
    </div>
</body>
<script>
    function submitData() {
        var user = $("#username")[0].value;
        var pass = $("#password")[0].value;
        console.log(user);
        var param = "username=" + user;
        param += "&password=" + pass;
        $.ajax({
            url: "/user/login.do",
            type: "POST",
            data: param,
            dataType: "json",
            success: loadData,
            error: showError
        });
    }
    function loadData(res) {
        var answer = res.data['answer'];
        var email = res.data['email'];
        var id = res.data['id'];
        var phone = res.data['phone'];
        var question = res.data['question'];
        var username = res.data['username'];
        var role = res.data['role'];
        var param = "answer=" + answer;

        param += "&email=" + email;
        param += "&id=" + id;
        param += "&phone=" + phone;
        param += "&question=" + question;
        param += "&username=" + username;
        param += "&role=" + role;
        console.log(param);
        $.ajax({
            url: "/user/loginDetail.do",
            type: "POST",
            data: param,
            dataType: "json",
            async: false,
            success: load,
            error: showError
        });

    }
    function showError() {
        window.location.href = "/user/loginDetail.do";
    }
    function load(res) {
        window.location.href = "/user/loginDetail.do";
    }

</script>




















</html>

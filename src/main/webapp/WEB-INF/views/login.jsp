<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebDesign--设计师的充电站</title>
    <script type="application/javascript">
        function refreshVerificationCode() {
            document.getElementById("captchaImg").src="captcha.jpg?"+Math.random();
        }
    </script>
</head>
<body>
登录页面
<form method="post" action="doLogin">
    用户名:<input type="text" name="username">
    密码:<input type="password" name="password">
    验证码:<input type="text" name="captcha">
    <img id="captchaImg" src="captcha.jpg" title="点击刷新" alt="验证码" width="100px;" height="40px;" style="cursor: pointer;" onclick="refreshVerificationCode()">
    在该电脑上记录我的登录状态<input type="checkbox" name="remember-me" >
    <input type="submit">
</form>
</body>
</html>
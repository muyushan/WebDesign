<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebDesign--设计师的充电站</title>

<link href="<s:url value="/static_resources/css/regist.css" ></s:url>" rel="stylesheet" type="text/css" />

<script src="<s:url value="/static_resources/js/jquery-3.1.1.min.js" />" type="text/javascript" ></script>
<script src="<s:url value="/static_resources/js/regist.js" />" type="text/javascript" ></script>
</head>
<body>
<form method="post">
<div id="regist_body">
<div class="regist_row"><input type="text" placeholder="邮箱|手机" id="email_phone"/><span class="valid_tipspan"></span></div>
<div class="regist_row"><input type="password" placeholder="密码" id="password"/><span class="valid_tipspan"></span></div>
<div class="regist_row"><input type="password" placeholder="确认密码" id="confirm_password"/><span class="valid_tipspan"></span></div>
<div class="regist_row"><button type="button" id="submitBtn">马上注册</button></div>
</div>
</form>
</body>
</html>
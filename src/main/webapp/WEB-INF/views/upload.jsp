<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebDesign--设计师的充电站</title>
</head>
<body>
上传头像
<form action="upload.do" method="post" enctype="multipart/form-data">
    请选择头像:<input type="file" name="image"/>
    <input type="text" name="age">
    <textarea name="cont" cols="10" rows="10"></textarea>
    <textarea name="cont2" cols="10" rows="10"></textarea>
    <input type="submit"></input>
</form>
</body>
</html>
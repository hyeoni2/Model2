<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test01</title>
</head>
<body>
<%@include file="../../include/inc_menu.jsp" %>

<h2>test01</h2>
<form name="form" method="post" action="${path }/test_servlet/test01Proc.do">
이름 : <input type="text" name="name"><br>
주민번호 : <input type="text" name="jumin"><br>
<button type="submit">확인</button>
</form>
</body>
</html>
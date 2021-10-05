<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../../include/inc_menu.jsp" %>
<form name="form" method="post" action="${path}/test_servlet/test03Proc.do }">
	이름 : <input type="text" name="name"><br>
	성별 : <input type="text" name="gender"><br>
	주민번호 : <input type="text" name="jumin"><br>
	나이 : <input type="text" name="age"><br>
	<button type="submit">확인</button>
</form>

</body>
</html>
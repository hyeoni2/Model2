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
<h2>test09</h2>
<form name="form" method="post" action="${path }/test_servlet/test09Proc.do">
	이름 : <input type="text" name="name"><br>
	국어 : <input type="text" name="kor"><br>
	영어 : <input type="text" name="eng"><br>
	수학 : <input type="text" name="mat"><br>
	사회 : <input type="text" name="soc"><br>
	과학 : <input type="text" name="sci"><br>
	역사 : <input type="text" name="his"><br>
<button type="submit">등록</button>
</form>

</body>
</html>
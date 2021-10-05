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
<h2>test07</h2>
<form name="form" method = "post" action="${path }/test_servlet/test07Proc.do">	
	상품 분류: <input type="text" name="gubun"><br>
	상품 이름: <input type="text" name="name"><br>
	상품 가격: <input type="text" name="price"><br>
	상품 할인율(%): <input type="text" name="dc"><br>
	상품 할인금액: <input type="text" name="dcamount"><br>
	제조사: <input type="text" name="brand"><br>
	<button type="submit">확인</button>
</form>	
</body>
</html>
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
<h2>test08</h2>
<form name="form" method = "post" action="${path }/test_servlet/test07Proc.do">	

	상품 분류: ${map.gubun }<br>
	상품 이름: ${ map.name}<br>
	상품 가격: ${map.price }<br>
	상품 할인율(%): ${map.dc }<br>
	상품 할인금액: ${ map.dcamount}<br>
	제조사:${map.brand } <br>
	<button type="submit">확인</button>
</form>	
</body>
</html>
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
상품 분류 :${ guhun }<br>
상품 이름 :${ name }<br>
상품 가격 :${ price }<br>
할인율 :${ dc }<br>
할인금액 :${ dcamount }<br>
제조사 :${ brand }<br>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../include/inc_menu.jsp" %>
<h2>오류 메세지</h2>
<c:choose>
	<c:when test="${code eq 'login'}">
		로그인하시고 이용하세요
		<br>
	<a href="${path }">로그인하기</a>
		
	</c:when>
	<c:when test="${code eq 'chugaErr'}">
		장바구니 추가 중 오류가 발생했습니다.
			<br>
	<a href="${path }/shop_servlet/List.do">쇼핑몰로</a>
	</c:when>
	
	<c:otherwise>
		dpfj
	</c:otherwise>

</c:choose>
</body>
</html>
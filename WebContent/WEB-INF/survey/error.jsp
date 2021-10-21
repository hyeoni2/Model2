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
	<c:when test="${code eq 'chugaEr'}">
		설문조사 등록 중 오류가 발생했습니다.
			<br>
	<a href="${path }/survey_servlet/Chuga.do">추가하기로</a>
	</c:when>
	<c:when test="${code eq 'answerEr'}">
		설문진행 중 오류가 발생했습니다..
			<br>
	<a href="${path }/survey_servlet/List.do">설문조사 목록으로</a>
	</c:when>
	<c:when test="${code eq 'sujungEr'}">
		설문조사 수정 중 오류가 발생했습니다.
			<br>
	<a href="${path }/survey_servlet/Sujung.do">수정하기로</a>
	</c:when>
	<c:when test="${code eq 'sakjaeEr'}">
		설문조사 삭제 중 오류가 발생했습니다.
			<br>
	<a href="${path }/survey_servlet/Sakjae.do">삭제하기로</a>
	</c:when>
	
	<c:otherwise>
		dpfj
	</c:otherwise>

</c:choose>
</body>
</html>
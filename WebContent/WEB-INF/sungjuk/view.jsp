<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../include/inc_menu.jsp"  %>
<h2> 성적 상세 목록 </h2>
<c:forEach var="dto" items="${list }">
<table border="1">
	<tr>
		<td>아이디</td>
		<td>${dto.id }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td>시험명</td>
		<td>${dto.sihum_name }</td>
	</tr>
	
	<tr>
		<td>국어</td>
		<td>${dto.kor }</td>
	</tr>
	<tr>
		<td>영어</td>
		<td>${dto.eng }</td>
	</tr>
	<tr>
		<td>수학</td>
		<td>${dto.mat }</td>
	</tr>
	<tr>
		<td>과학</td>
		<td>${dto.sci }</td>
	</tr>
	<tr>
		<td>역사</td>
		<td>${dto.his }</td>
	</tr>
	<tr>
		<td>총점</td>
		<td>${dto.tot }</td>
	</tr>
	<tr>
		<td>평균</td>
		<td>${dto.avg }</td>
	</tr>
	<tr>
		<td>등급</td>
		<td>${dto.grade }</td>
	</tr>
	<tr>
		<td>등록일</td>
		<td>${dto.regi_date }</td>
	</tr>
</table>
<%--
 |
<a href="${path }/sungjuk_servlet/sungjukChuga.do">등록</a>
|
<a href="${path }/sungjuk_servlet/sungjukSujung.do?no=${dto.no}">수정</a>
|
<a href="${path }/sungjuk_servlet/sungjukList.do">목록</a>
|
<a href="${path }/sungjuk_servlet/sungjukSakjae.do?no=${dto.no}">삭제</a>
| 
--%>

<c:choose>
	<c:when test="${sessionScope.cookAbility eq 'A'}">
		|
		<a href="${path }/sungjuk_servlet/Chuga.do">등록</a>
		|
		
		<a href="${path }/sungjuk_servlet/Sujung.do?no=${dto.no}">수정</a>
		|
		
		<a href="${path }/sungjuk_servlet/List.do">목록</a>
		|
		<a href="${path }/sungjuk_servlet/Sakjae.do?no=${dto.no}">삭제</a>
		|
	</c:when>	
	<c:when test="${sessionScope.cookAbility eq 'B'}">
		|
		<a href="${path }/sungjuk_servlet/Chuga.do">등록</a>
		|
		
		<a href="${path }/sungjuk_servlet/Sujung.do?no=${dto.no}">수정</a>
		|
		<a href="${path }/sungjuk_servlet/List.do">목록</a>
		|
	</c:when>
	<c:otherwise>
		|
		<a href="${path }/sungjuk_servlet/List.do">목록</a>
		|
	</c:otherwise>
</c:choose>

</c:forEach>
</body>
</html>
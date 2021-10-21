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
<h2> 성적 목록 </h2>
<table border="1">
	<tr>
		<th>순번</th>
		<th>이름</th>
		<th>시험명</th>
		<th>국어</th>
		<th>영어</th>
		<th>수학</th>
		<th>과학</th>
		<th>역사</th>
		<th>평균</th>
		<th>등록일</th>
	</tr>
<c:forEach var="dto" items="${list }">
	<tr>
		<td>${dto.no }</td>
		<td>
			<a href="${path}/sungjuk_servlet/View.do?no=${dto.no}">
				${dto.name }
			</a>
		</td>
		<td>${dto.sihum_name }</td>
		<td>${dto.kor }</td>
		<td>${dto.eng }</td>
		<td>${dto.mat }</td>
		<td>${dto.sci }</td>
		<td>${dto.his }</td>
		<td>${dto.avg }</td>
		<td>${dto.regi_date }</td>
	</tr>
</c:forEach>
<c:if test="${fn:length(list ) == 0}">
	<tr>
		<td colspan="10">
			등록된 내용이 없습니다.
		</td>
	</tr>
	</c:if>		
</table>
<br>
<c:if test="${sessionScope.cookAbility eq 'A' || sessionScope.cookAbility eq 'B' }">
|
<a href="${path }/sungjuk_servlet/Chuga.do">등록</a>
|
</c:if>
</body>
</html>
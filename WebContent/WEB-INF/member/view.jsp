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
<h2>회원 상세 정보</h2>
<c:forEach items="${list }" var="dto">
<table border="1" width = 30%>
		<tr>
			<td>
				권한
			</td>
	
			<td>
				${dto.ability }
			</td>
		</tr>
		<tr>
			<td>
				아이디
			</td>
	
			<td>
				${dto.id }
			</td>
		</tr>
		<tr>
			<td>
				이름
			</td>
	
			<td>
				${dto.name}
			</td>
		</tr>
		<tr>
			<td>
				전화번호
			</td>
	
			<td>
				${dto.phone }
			</td>
		</tr>
		<tr>
			<td>
				주소
			</td>
	
			<td>
				${dto.addr}
			</td>
		</tr>
		<tr>
			<td>
				등록일
			</td>
	
			<td>
				${dto.regi_date}
			</td>
		</tr>

		
</table>

<c:choose>
	<c:when test="${sessionScope.cookAbility eq 'A'}">
		|
		<a href="${path }/member_servlet/Chuga.do">등록</a>
		|
		
		<a href="${path }/member_servlet/Sujung.do?no=${dto.no}">수정</a>
		|
		
		<a href="${path }/member_servlet/List.do">목록</a>
		|
		<a href="${path }/member_servlet/Sakjae.do?no=${dto.no}">삭제</a>
		|
	</c:when>	
	<c:when test="${sessionScope.cookAbility eq 'B'}">
		|
		<a href="${path }/member_servlet/Chuga.do">등록</a>
		|
		
		<a href="${path }/member_servlet/Sujung.do?no=${dto.no}">수정</a>
		|
		<a href="${path }/member_servlet/List.do">목록</a>
		|
	</c:when>
	<c:otherwise>
		|
		<a href="${path }/member_servlet/List.do">목록</a>
		|
	</c:otherwise>
</c:choose>


</c:forEach>
</body>
</html>
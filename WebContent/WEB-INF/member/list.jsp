<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<%@include file="../include/inc_menu.jsp"  %>
<h2>회원목록</h2>
<table border="1">
	<tr>
		<th>순번</th>
		<th>아이디</th>
		<th>이름</th>
		<th>연락처</th>
		<th>주소</th>
		<th>등록일</th>
	</tr>


 <c:forEach var="dto" items="${list }" >
	
	<tr>
		<td>
				${dto.no }
		</td>
	
		<td>
			<a href="${path }/member_servlet/View.do?no=${dto.no}">
				${dto.id }
			</a>
		</td>
	
		<td>
			${dto.name }
		</td>
	
		<td>
				${dto.phone }
		</td>
	
	
		<td>
				${dto.addr }
		</td>
	
		<td>
				${dto.regi_date }
		</td>
	
	</tr>
	
</c:forEach>	
<c:if test="${fn:length(list ) == 0}">
	<tr>
		<td colspan="6">
			등록된 내용이 없습니다.
		</td>
	</tr>
	</c:if>		
</table>
<br>
<c:if test="${sessionScope.cookAbility eq 'A' || sessionScope.cookAbility eq 'B' }">

|
<a href = "${path}/member_servlet/Chuga.do">등록</a>
|

</c:if>
</body>
</html>
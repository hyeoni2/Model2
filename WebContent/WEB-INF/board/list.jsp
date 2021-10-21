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
<h2>게시판</h2>

<table border="1" width = 80%>
	<tr>
		<td>
			순번
		</td>
		<td>
			작성자
		</td>
		<td>
			제목
		</td>
		<td>
			추천
		</td>
		<td>
			등록일
		</td>
	</tr>
<c:forEach var="dto" items="${list }">
	<tr>
		<td>
			${dto.no }
		</td>
		<td>
			<c:forEach var="i" begin="1" end="${dto.re_step}" step="1">		

				<c:set var="jump" value = ""></c:set>
				&nbsp;
			</c:forEach>	
				<a href="${path }/board_servlet/View.do?no=${dto.no}">
				${jump}${dto.writer }
				</a>
		</td>
		<td>
			<c:forEach var="i" begin="1" end="${dto.re_step }" step="1">
				<c:if test="${dto.re_step > 1 }">
					<c:set var="reVar" value = "[@re]"></c:set>
				</c:if>
			</c:forEach>
					${reVar }${dto.subject }
		</td>
		<td>
			${dto.hit }
		</td>
		<td>
			${dto.regi_date }
		</td>
	</tr>

</c:forEach>	
</table>

|
<a href = "${path}/board_servlet/Chuga.do">등록</a>
|
</body>
</html>
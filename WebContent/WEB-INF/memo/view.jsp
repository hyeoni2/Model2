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
<h2>메모 상세 정보</h2>
<c:forEach items="${list }" var="dto">
<table border="1">
		<tr>
			<td>
				순번
			</td>
	
			<td>
				${dto.no }
			</td>
		</tr>
		<tr>
			<td>
				작성자
			</td>
	
			<td>
				${dto.writer}
			</td>
		</tr>
		<tr>
			<td>
				내용
			</td>
	
			<td>
				${dto.content }
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
|
<a href="${path }/memo_servlet/Chuga.do">등록</a>
|
<a href="${path }/memo_servlet/Sujung.do?no=${dto.no}">수정</a>
|
<a href="${path }/memo_servlet/List.do">목록</a>
|
<a href="${path }/memo_servlet/Sakjae.do?no=${dto.no}">삭제</a>
|

</c:forEach>
</body>
</html>
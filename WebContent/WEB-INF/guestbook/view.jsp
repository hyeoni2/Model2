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
<h2>방명록 상세 정보</h2>
<c:forEach items="${list }" var="dto">
<table border="1">
		<tr>
			<td>
				이름
			</td>
	
			<td>
				${dto.name }
			</td>
		</tr>
		<tr>
			<td>
				이메일
			</td>
	
			<td>
				${dto.email}
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
<a href="${path }/guestbook_servlet/guestbookChuga.do">등록</a>
|
<a href="${path }/guestbook_servlet/guestbookSujung.do?no=${dto.no}">수정</a>
|
<a href="${path }/guestbook_servlet/guestbookList.do">목록</a>
|
<a href="${path }/guestbook_servlet/guestbookSakjae.do?no=${dto.no}">삭제</a>
|

</c:forEach>
</body>
</html>
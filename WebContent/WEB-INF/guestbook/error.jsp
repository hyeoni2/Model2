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
<h2>방명록 에러</h2>
<form name = "chugaForm">	
	<table border="0">
		<tr>
			<td>메모</td>
			<td>${memo }</td>
		</tr>
		<tr>
			<td colspan="2">
				<a href="${path}/guestbook_servlet/List.do">목록으로</a>
			</td>
			
		</tr>
	</table>
</form>	
</body>
</html>

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
<h2>방명록 삭제</h2>
<c:forEach var="dto" items="${list }">
<form name = "sakjaeForm">	
<input type="hidden" name="no" value="${dto.no }">
	<table>
		<tr>
			<td>이름</td>
			<td>${dto.name }</td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${dto.email }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" readonly>${dto.content }</textarea>
			</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td>
				${dto.regi_date }
			</td>
		</tr>


		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="sakjae();">삭제하기</button>
				<button type="button" onclick="list();">목록으로</button>
			</td>
		</tr>
	</table>
</form>
</c:forEach>	
</body>
</html>


<script>

	
	function list() {
		location.href = '${path}/guestbook_servlet/List.do';
	}

	function sakjae() {
		

		if(confirm('삭제하시겠습니까?')){
			sakjaeForm.method = 'post';
			sakjaeForm.action = '${path}/guestbook_servlet/SakjaeProc.do';
			sakjaeForm.submit();
		}	
	}
</script>

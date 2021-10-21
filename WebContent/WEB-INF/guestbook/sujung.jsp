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
<h2>방명록 수정</h2>
<c:forEach var="dto" items="${list }">
<form name = "sujungForm">	
<input type="hidden" name="no" value="${dto.no }">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id="name" value="${dto.name }"></td>
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
				<textarea name="content" >${dto.content }</textarea>
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
				<button type="button" onclick="sujung();">수정하기</button>
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

	function sujung() {
		

		if(confirm('수정하시겠습니까?')){
			sujungForm.method = 'post';
			sujungForm.action = '${path}/guestbook_servlet/SujungProc.do';
			sujungForm.submit();
		}	
	}
</script>

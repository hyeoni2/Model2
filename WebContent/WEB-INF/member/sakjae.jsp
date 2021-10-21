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
<h2>회원 삭제</h2>
<c:forEach items="${list }" var="dto">
	<form name = "sakjaeForm">	
			<input type="hidden" name="no" value="${dto.no }">
		<table border="1" width = 30%>	
				<tr>
					<td>아이디</td>
					<td>${dto.id}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${dto.name }</td>
				</tr>
				<tr>
					<td>연락처</td>
					<td>${dto.phone }</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>${dto.addr }</td>
				</tr>
				<tr>
					<td>
						등록일
					</td>
			
					<td>
						${dto.regi_date}
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
		location.href = '${path}/member_servlet/List.do';
	}

	function sakjae() {
		
		sakjaeForm.method = 'post';
		sakjaeForm.action = '${path}/member_servlet/SakjaeProc.do';
		sakjaeForm.submit();
	}
</script>


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
<h2>상품 삭제</h2>
<c:forEach items="${list }" var="dto">
	<form name = "sakjaeForm">	
			<input type="hidden" name="no" value="${dto.no }">
		<table border="1" width = 30%>	
				<tr>
					<td>작성자</td>
					<td>${dto.writer}</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>${dto.content }</td>
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
		location.href = '${path}/memo_servlet/List.do';
	}

	function sakjae() {
		
		sakjaeForm.method = 'post';
		sakjaeForm.action = '${path}/memo_servlet/SakjaeProc.do';
		sakjaeForm.submit();
	}
</script>


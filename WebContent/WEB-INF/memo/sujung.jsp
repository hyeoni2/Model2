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
<h2>회원 수정</h2>
<c:forEach items="${list }" var="dto">
	<form name = "sujungForm">	
			<input type="hidden" name="no" value="${dto.no }">
		<table border="1">	
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" value="${dto.writer}"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="content">${dto.content }</textarea></td>
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
		location.href = '${path}/memo_servlet/memoList.do';
	}

	function sujung() {
		
		sujungForm.method = 'post';
		sujungForm.action = '${path}/memo_servlet/memoSujungProc.do';
		sujungForm.submit();
	}
</script>


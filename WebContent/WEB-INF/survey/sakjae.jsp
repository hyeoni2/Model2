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
<h2>설문 삭제</h2>
<c:forEach var="dto" items="${list }">
<form name = "sakjaeForm">	
	<input type="hidden" name="no" value="${dto.no }">
	<table border="1">
		<tr>
			<td>설문 질문</td>
			<td>${dto.question }</td>
		</tr>
		<tr>
			<td>답변 1</td>
			<td>
				${dto.ans1 }
			</td>
		</tr>
		<tr>
			<td>답변 2</td>
			<td>
				${dto.ans2 }
			</td>
		</tr>
		<tr>
			<td>답변 3</td>
			<td>
				${dto.ans3 }
			</td>
		</tr>
		<tr>
			<td>답변 4</td>
			<td>
				${dto.ans4 }
			</td>
		</tr>
		<tr>
			<td>진행상태</td>
			<td>
				${dto.status }
			</td>
		</tr>
		<tr>
			<td align="center">시작날짜</td>
			<td>
				${dto.start_date }
			</td>
		</tr>
		<tr>
			<td>종료날짜</td>
			<td>
				${dto.last_date }
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

<script>

	function list() {
		location.href = '${path}/survey_servlet/List.do';
	}
	

	function sakjae() {
		
		if(confirm('삭제하시겠습니까?')){
			sakjaeForm.action = '${path}/survey_servlet/SakjaeProc.do';
			sakjaeForm.method = 'post';
			sakjaeForm.submit();
		}	
	}
</script>
</html>





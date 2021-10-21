<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

$(function() {
	/* alert('추가페이지'); */
	$('#question').focus();
});
</script>

</head>
<body>
<%@include file="../include/inc_menu.jsp"  %>
<h2>설문수정</h2>
<c:forEach var="dto" items="${list }">
<form name = "sujungForm">	
	<input type="hidden" name="no" value="${dto.no }">
	<table>
		<tr>
			<td>설문 질문</td>
			<td><input type="text" name="question" id="question" value="${dto.question }"></td>
		</tr>
		<tr>
			<td>답변 1</td>
			<td>
				<input type="text" name="ans1" id="ans1" value="${dto.ans1 }" >
			</td>
		</tr>
		<tr>
			<td>답변 2</td>
			<td>
				<input type="text" name="ans2" id="ans2" value="${dto.ans2 }">
			</td>
		</tr>
		<tr>
			<td>답변 3</td>
			<td>
				<input type="text" name="ans3" id="ans3" value="${dto.ans3 }">
			</td>
		</tr>
		<tr>
			<td>답변 4</td>
			<td>
				<input type="text" name="ans4" id="ans4"  value="${dto.ans4 }">
			</td>
		</tr>
		<tr>
			<td>진행상태</td>
			<td>
				<c:choose>
					<c:when test="${dto.status  == 1 }">
						<input type="radio" name="status"  value="0">종료
						<input type="radio" name="status" value="1" checked>진행중				
					</c:when>
					<c:otherwise>
						<input type="radio" name="status"  value="0" checked>종료
						<input type="radio" name="status" value="1" >진행중				
					</c:otherwise>
				</c:choose>
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
				<button type="button" onclick="sujung();">수정하기</button>
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
	

	function sujung() {
		
		if(confirm('수정하시겠습니까?')){
			sujungForm.action = '${path}/survey_servlet/SujungProc.do';
			sujungForm.method = 'post';
			sujungForm.submit();
		}	
	}
</script>
</html>





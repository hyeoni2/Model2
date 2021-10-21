<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js">
</script>
</head>
<body>
<%@include file="../include/inc_menu.jsp"  %>
<h2>설문지</h2>
<c:forEach var="dto" items="${list }">
<form name="answerForm">
<input type="hidden" name="no" value="${dto.no }">
<input type="hidden" name="input_answer" id="input_answer">
<span id="span_answer" style="display: none"></span>
	<table border="1">
		<tr>
			<td>
				 Q) ${dto.question } (응시자수 : ${dto.count } )
			</td>
		</tr>
		<tr>
			<td>
				 <a href="#" onclick="click_answer('1');"><span id="mun1">①</span></a>
				 <a href="#" onclick="click_answer('1');">${dto.ans1}</a>
			</td>
		</tr>	
		<tr>	
			<td>
				 <a href="#" onclick="click_answer('2');"><span id="mun2">②</span></a>
				 <a href="#" onclick="click_answer('2');">${dto.ans2}</a>
			</td>
		</tr>
		<tr>
			<td>
				 <a href="#" onclick="click_answer('3');"><span id="mun3">③</span></a>
				 <a href="#" onclick="click_answer('3');">${dto.ans3}</a>
			</td>
		</tr>
		<tr>	
			<td>
				 <a href="#" onclick="click_answer('4');"><span id="mun4">④</span></a>
				 <a href="#" onclick="click_answer('4');">${dto.ans4}</a>
			</td>
			
			<%--
			 	<input type="radio" name="answer" value="1">${dto.ans1 }
				<input type="radio" name="answer" value="2">${dto.ans2 }
				<input type="radio" name="answer" value="3">${dto.ans3 }
				<input type="radio" name="answer" value="4">${dto.ans4 } 
				--%>
		
		</tr>
		<tr>
			<td>
				
				 상태 : 	${dto.status }
 			</td>
		</tr>
		<tr>
			<td>
				 기간 : ${dto.start_date } ~ ${dto.last_date }
			</td>
		</tr>
		<tr>
			<td>
				 등록일 : 	${dto.regi_date }
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				 <button type="button" onclick="save();">설문저장하기</button>
			</td>
		</tr>
	</table>
</form>

	|
	<a href="${path }/survey_servlet/Chuga.do">등록</a>
	|
	
	<a href="${path }/survey_servlet/Sujung.do?no=${dto.no}">수정</a>
	|
	
	<a href="${path }/survey_servlet/List.do">목록</a>
	|
	<a href="${path }/survey_servlet/Sakjae.do?no=${dto.no}">삭제</a>
	|
</c:forEach>
</body>
<script type="text/javascript">
function click_answer(value1) {
	
	$("#input_answer").val(value1);
	
	$("#span_answer").text(value1);
	
	if (value1 == '1') {
		$("#mun1").text('❶');
		$("#mun2").text('②');
		$("#mun3").text('③');
		$("#mun4").text('④');
	} else if (value1 == '2') {
		$("#mun1").text('①');
		$("#mun2").text('❷');
		$("#mun3").text('③');
		$("#mun4").text('④');
	} else if (value1 == '3') {
		$("#mun1").text('①');
		$("#mun2").text('②');
		$("#mun3").text('❸');
		$("#mun4").text('④');
	} else if (value1 == '4') {
		$("#mun1").text('①');
		$("#mun2").text('②');
		$("#mun3").text('③');
		$("#mun4").text('❹');
	}
}

function save() {
	
	if(confirm("설문을 저장하시겠습니까?")){
		answerForm.action="${path }/survey_servlet/ViewProc.do"; 
		answerForm.method="post";
		answerForm.submit();
	}
}

</script>
</html>
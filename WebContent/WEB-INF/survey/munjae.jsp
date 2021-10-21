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
<h2>설문조사</h2>
<c:set var = "i" value="${i = 0 }"></c:set>
 <form name="munjaeForm">
 <c:forEach var="dto" items="${list }" >
	<input type="text" name="no_${i }"  value="${dto.no }" >
<table border="1" width = 800px>
	<tr>
		<td>
			${dto.no }번문제) ${dto.question }<input type="text" id="dab_${i }" name="dab_${i }" >${i }
		</td>
	</tr>
		
		<tr>
			<td>
				 <a href="#" onclick="click_answer('1' ,'${i }' );"><span id="mun1_${i }">①</span></a>
				 <a href="#" onclick="click_answer('1','${i }' );">${dto.ans1}</a>
			</td>
		</tr>	
		<tr>	
			<td>
				 <a href="#" onclick="click_answer('2','${i }' );"><span id="mun2_${i }">②</span></a>
				 <a href="#" onclick="click_answer('2','${i }' );">${dto.ans2}</a>
			</td>
		</tr>
		<tr>
			<td>
				 <a href="#" onclick="click_answer('3','${i }' );"><span id="mun3_${i }">③</span></a>
				 <a href="#" onclick="click_answer('3','${i }' );">${dto.ans3}</a>
			</td>
		</tr>
		<tr>	
			<td>
				 <a href="#" onclick="click_answer('4','${i }' );"><span id="mun4_${i }">④</span></a>
				 <a href="#" onclick="click_answer('4','${i }' );">${dto.ans4}</a>
			</td>
	</tr>

	<c:if test="${fn:length(list ) == 0}">

			등록된 내용이 없습니다.
	</c:if>		
	</table>
<br>
<c:set var = "i" value="${i = i + 1 }"></c:set>
</c:forEach>
<input type="text" name="count" value="${i }" >${i }
	<br>
		<div>
			<button type="button" onclick="save();">제출하기</button>
			<button type="button" onclick="list();">목록으로</button>
			
		</div>
</form>		
</body>


<script type="text/javascript">
function click_answer(value1, value2) {
	
	$("#dab_"+value2).val(value1);
	
	if (value1 == '1') {
		$("#mun1_"+value2).text('❶');
		$("#mun2_"+value2).text('②');
		$("#mun3_"+value2).text('③');
		$("#mun4_"+value2).text('④');
	} else if (value1 == '2') {
		$("#mun1_"+value2).text('①');
		$("#mun2_"+value2).text('❷');
		$("#mun3_"+value2).text('③');
		$("#mun4_"+value2).text('④');
	} else if (value1 == '3') {
		$("#mun1_"+value2).text('①');
		$("#mun2_"+value2).text('②');
		$("#mun3_"+value2).text('❸');
		$("#mun4_"+value2).text('④');
	} else if (value1 == '4') {
		$("#mun1_"+value2).text('①');
		$("#mun2_"+value2).text('②');
		$("#mun3_"+value2).text('③');
		$("#mun4_"+value2).text('❹');
	}
}

function save() {
	
	if(confirm("설문을 저장하시겠습니까?")){
		munjaeForm.action="${path }/survey_servlet/MunjaeProc.do"; 
		munjaeForm.method="post";
		munjaeForm.submit();
	}
}

</script>
</html>
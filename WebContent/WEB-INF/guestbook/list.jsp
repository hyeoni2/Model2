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


<h2>방명록 목록</h2>
<span id="click" onclick="clickPro();">[등록]</span>
<div id="hidePro" style="display: none">
<form name = "saveForm" id="saveForm">	
<span id="gubun" style="display: none;"></span>
<input type="hidden" name="no" id="no" value="${dto.no }">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id="name" ></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" id="email"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content" id="content"></textarea>
			</td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="passwd" id="passwd"></td>
		</tr>


		<tr>
			<td colspan="2" align="center">
			
				<button type="button" onclick="submitPro('');" id="chugaBtn">등록하기</button>
				
				<button type="button" onclick="clearPro();" id="clear" style="display: none;">초기화</button>
			</td>
		</tr>
	</table>
</form>	
</div>
<script>

function clickPro() {
	$("#hidePro").show();	
	$("#click").hide();
	
}


function sujungPro(value1, value2, value3, value4) {
	
	$("#gubun").text("M");
	
	$("#no").val(value1);
	$("#name").val(value2);
	$("#email").val(value3);
	$("#content").val(value4);
	
	
	$("#chugaBtn").text("수정하기");
	$("#clear").show();	
	$("#hidePro").show();	
	$("#click").hide();
	
	
}

function clearPro() {
	
	$("#no").val("");
	$("#name").val("");
	$("#email").val("");
	$("#content").val("");
	
	$("#chugaBtn").text("등록하기");
	$("#clear").hide();

	
	
	
}

function deletePro(value1, value2, value3, value4) {
	
		$("#gubun").text("D");
		
		$("#no").val(value1);
		$("#name").val(value2);
		$("#email").val(value3);
		$("#content").val(value4);
		
		$("#chugaBtn").text("삭제하기");
		$("#clear").show();
		$("#hidePro").show();	
		$("#click").hide();
}

function submitPro(value1) {
	
	var value1 = $("#gubun").text();
	
	if($("#name").val() == ""){
		alert('이름을 입력해주세요');
		$("#name").focus();
		return;
	}
	if($("#email").val() == ""){
		alert('이메일을 입력해주세요');
		$("#email").focus();
		return;
	}
	if($("#content").val() == ""){
		alert('내용을 입력해주세요');
		$("#content").focus();
		return;
	}
	
	
	if(value1 == "M"){
		url = "${path}/guestbook_servlet/SujungProc.do";
	}else if(value1 == "D"){
		url = "${path}/guestbook_servlet/SakjeProc.do";
	}else{
		url = "${path}/guestbook_servlet/ChugaProc.do";
		
	}
	
	saveForm.method = 'post';
	saveForm.action = url;
	saveForm.submit();
	
}


</script>
<hr>
<c:forEach var="dto" items="${list }">
<table border="1" >
	<tr>
		<th>순번</th>
		<th>이름</th>
		<th width="200px">이메일</th>
		<th>내용</th>
		<th>등록일</th>
		<th>비고</th>
	</tr>

	<tr>
	
		<td>${dto.no }</td>
		<td>
			<a href="${path }/guestbook_servlet/View.do?no=${dto.no}">
				${dto.name }
			</a>
		</td>
		<td>${dto.email }</td>
		<td>${dto.content }</td>
		<td>${dto.regi_date }</td>
		<td>
		<a href="#" onclick="sujungPro('${dto.no}', '${dto.name }' ,'${dto.email }', '${dto.content }');">
		수정
		</a>
		/
		<a href="#"  onclick="deletePro('${dto.no}', '${dto.name }', '${dto.email }', '${dto.content }');">
		삭제
		</a>
		</td>		
	</tr>
	
<c:if test="${fn:length(list) == 0 }">
 <tr>
 	<td colspan="5">
 		등록된 내용이 없습니다
 	</td>
 </tr>
 </c:if>

</table>
<br>
</c:forEach>
</body>
</html>
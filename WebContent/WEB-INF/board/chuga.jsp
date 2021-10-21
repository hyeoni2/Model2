<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

$(function() {
	/* alert('추가페이지'); */
	$('#writer').focus();
});
</script>

</head>
<body>

<%@include file="../include/inc_menu.jsp"  %>
<h2>게시판등록</h2>
<c:set var="dto" value="${dto }"></c:set>
<form name = "chugaForm">	
				<input type="text" name="no" value="${dto.no }">
			<table>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" id="writer" value="${dto.writer }"></td>
				</tr>
				
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" id="subject"  value="${dto.subject }"></td>
				</tr>
				
				<tr>
					<td>내용</td>
					<td><textarea style="width: 170px;" name="content" id="content">${dto.content }</textarea></td>
		
				</tr>
				
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" id="email"></td>
				</tr>
				
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="passwd" id="passwd"></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" onclick="chuga();">등록하기</button>
						<button type="button" onclick="list();">목록으로</button>
					</td>
				</tr>
			</table>
</form>	


<script>

	
	function list() {
		location.href = '${path}/board_servlet/List.do';
	}

	function chuga() {

		
		if(chugaForm.writer.value == ""){
			alert('작성자를 입력해주세요');
			chugaForm.writer.focus();
			return;
		}
		if(chugaForm.subject.value == ""){
			alert('제목을 입력해주세요');
			chugaForm.subject.focus();
			return;
		}
		if(chugaForm.content.value == ""){
			alert('내용을 입력해주세요');
			chugaForm.content.focus();
			return;
		}
		if(chugaForm.email.value == ""){
			alert('이메일을 입력해주세요');
			chugaForm.email.focus();
			return;
		}
		if(chugaForm.passwd.value == ""){
			alert('패스워드를 입력해주세요');
			chugaForm.passwd.focus();
			return;
		}

		
		
		if(confirm('등록하시겠습니까?')){
			chugaForm.action = '${path}/board_servlet/ChugaProc.do';
			chugaForm.method = 'post';
			chugaForm.submit();
		}	
	}
</script>
</body>
</html>
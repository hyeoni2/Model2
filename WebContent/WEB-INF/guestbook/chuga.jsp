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
	$('#name').focus();
});
</script>

</head>
<body>
<%@include file="../include/inc_menu.jsp"  %>
<h2>방명록 등록</h2>
<form name = "chugaForm">	
	<table border="1">
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id="name"></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea name="content"></textarea>
			</td>
		</tr>


		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="chuga();">등록하기</button>
				<button type="button" onclick="list();">목록으로</button>
			</td>
		</tr>
	</table>
</form>	
</body>


</html>


<script>

	
	function list() {
		location.href = '${path}/guestbook_servlet/guestbookList.do';
	}

	function chuga() {
		
		if(chugaForm.name.value == ""){
			alert('이름을 입력해주세요');
			chugaForm.name.focus();
			return;
		}
		
		if(chugaForm.email.value == ""){
			alert('이메일을 입력해주세요');
			chugaForm.email.focus();
			return;
		}
		
		if(chugaForm.content.value == ""){
			alert('내용을 입력해주세요');
			chugaForm.content.focus();
			return;
		}
		if(confirm('등록하시겠습니까?')){
			chugaForm.method = 'post';
			chugaForm.action = '${path}/guestbook_servlet/guestbookChugaProc.do';
			chugaForm.submit();
		}	
	}
</script>

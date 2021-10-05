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
	$('#id').focus();
});
</script>

</head>
<body>
<%@include file="../include/inc_menu.jsp"  %>
<h2>로그인</h2>
<form name = "loginForm">	
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" id="id"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="login();">로그인</button>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>


<script>

	function login() {
		
		if(loginForm.id.value == ""){
			alert('아이디를 입력해주세요');
			loginForm.id.focus();
			return;
		}
		
		if(loginForm.passwd.value == ""){
			alert('비밀번호를 입력해주세요');
			loginForm.passwd.focus();
			return;
		}			
			
		loginForm.method = 'post';
		loginForm.action = '${path}/member_servlet/memberLoginProc.do';
		loginForm.submit();
	
	}
</script>


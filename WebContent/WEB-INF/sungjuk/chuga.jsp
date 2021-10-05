<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

$(function() {
	/* alert('추가페이지'); */
	$('#id').focus();
});
</script>

<body>
<%@include file="../include/inc_menu.jsp"  %>

<h2> 성적 등록 </h2>
<form name="chugaForm">
	<table>
		<tr>
			<td>아이디 : </td>
			<td>
				<input type="text" name="id" id="id">
			</td>
		</tr>
		<tr>
			<td>이름 : </td>
			<td>
				<input type="text" name="name">
			</td>
		</tr>
		<tr>
			<td>시험명 : </td>
			<td>
				<input type="text" name="sihum_name">
			</td>
		</tr>
		
		<tr>
			<td>국어 : </td>
				<td>
				<input type="text" name="kor">
			</td>
		</tr>
		<tr>
			<td>영어 : </td>
			<td>
				<input type="text" name="eng">
			</td>
		</tr>
		<tr>
			<td>수학 : </td>
			<td>
				<input type="text" name="mat">
			</td>
		</tr>
		<tr>
			<td>과학 : </td>
			<td>
				<input type="text" name="sci">
			</td>
		</tr>
		<tr>
			<td>역사 : </td>
			<td>
				<input type="text" name="his">
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
<script type="text/javascript">

function list() {
	location.href = '${path}/sungjuk_servlet/sungjukList.do';
}

function chuga() {
	
	if(chugaForm.id.value == ""){
		alert('아이디를 입력해주세요.');
		chugaForm.id.focus();
		return;
	}
	
	if(chugaForm.name.value == ""){
		alert('이름을 입력해주세요.');
		chugaForm.name.focus();
		return;
	}
	if(chugaForm.sihum_name.value == ""){
		alert('시험명을 입력해주세요.');
		chugaForm.sihum_name.focus();
		return;
	}
	if(chugaForm.kor.value == ""){
		alert('국어성적을 입력해주세요.');
		chugaForm.kor.focus();
		return;
	}
	if(chugaForm.eng.value == ""){
		alert('영어성적을 입력해주세요.');
		chugaForm.eng.focus();
		return;
	}
	if(chugaForm.mat.value == ""){
		alert('수학성적을 입력해주세요.');
		chugaForm.mat.focus();
		return;
	}
	if(chugaForm.sci.value == ""){
		alert('과학성적을 입력해주세요.');
		chugaForm.sci.focus();
		return;
	}
	if(chugaForm.his.value == ""){
		alert('과학성적을 입력해주세요.');
		chugaForm.his.focus();
		return;
	}
	
	
	if(confirm('등록하시겠습니까?')){
		chugaForm.method = 'post';
		chugaForm.action = '${path}/sungjuk_servlet/sungjukChugaProc.do';
		chugaForm.submit();
	}
		
	
}


</script>
</html>
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
	$('#witer').focus();
});
</script>

</head>
<body>
<%@include file="../include/inc_menu.jsp"  %>
<h2>메모등록</h2>
<form name = "chugaForm">	
	<table>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" id="writer"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" style="width: 200px; height: 100px"></textarea></td>
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
		location.href = '${path}/memo_servlet/memoList.do';
	}

	function chuga() {
		
		if(chugaForm.writer.value == ""){
			alert('작성자를 입력해주세요');
			chugaForm.writer.focus();
			return;
		}
		
		if(chugaForm.content.value == ""){
			alert('내용을 입력해주세요');
			chugaForm.content.focus();
			return;
		}
		
		if(confirm('등록하시겠습니까?')){
			chugaForm.method = 'post';
			chugaForm.action = '${path}/memo_servlet/memoChugaProc.do';
			chugaForm.submit();
		}	
	}
</script>


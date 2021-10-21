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
<h2>상품등록</h2>
<form name = "chugaForm">	
	<table>
		<tr>
			<td>상품 이름</td>
			<td><input type="text" name="name" id="name"></td>
		</tr>
		<tr>
			<td>메인 이미지</td>
			<td>
				<input type="file" name="0" id="0" class="fileUp">
			</td>
		</tr>
		<tr>
			<td>상세 이미지1</td>
			<td>
				<input type="file" name="1" id="1" class="fileUp">
			</td>
		</tr>
		<tr>
			<td>상세 이미지2</td>
			<td>
				<input type="file" name="2" id="2" class="fileUp">
			</td>
		</tr>
		<tr>
			<td>
				<input type="hidden" name="count">
			</td>
		</tr>
		<tr>
			<td>상품 설명</td>
			<td><input type="text" name="description"></td>
		</tr>
		<tr>
			<td>상품 가격</td>
			<td><input type="text" name="price"></td>
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
		location.href = '${path}/product_servlet/List.do';
	}

	function chuga() {

		
		if(chugaForm.name.value == ""){
			alert('상품이름을 입력해주세요');
			chugaForm.name.focus();
			return;
		}
		
		if(chugaForm.price.value == ""){
			alert('상품가격을 입력해주세요');
			chugaForm.price.focus();
			return;
		}
		
		if(chugaForm.description.value == ""){
			alert('상품 설명을 입력해주세요');
			chugaForm.description.focus();
			return;
		}
		
		
		if(confirm('등록하시겠습니까?')){
			chugaForm.count.value = chugaForm.getElementsByClassName("fileUp").length;
			chugaForm.enctype = "multipart/form-data";
			chugaForm.action = '${path}/product_servlet/ChugaProc.do';
			chugaForm.method = 'post';
			chugaForm.submit();
		}	
	}
</script>


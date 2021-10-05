<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../../include/inc_menu.jsp" %>
<h2>test11</h2>
<form name="chugaForm">
이름 : <input type="text" name="name" id="name"><br>
전화번호 : <input type="text" name="phone" id="phone"><br>
사진 :	<input type="file" name="file"><br>
	<button type="button" onclick="chuga();">확인</button>
</form>

<script type="text/javascript">
	function chuga() {
		if(confirm('등록하시겠습니까?')){
			document.chugaForm.method = "post";
			document.chugaForm.action = "${path }/test_servlet/test11Proc.do" ;
			document.chugaForm.enctype = "multipart/form-data";
			document.chugaForm.submit();
		}
	}
</script>

</body>
</html>
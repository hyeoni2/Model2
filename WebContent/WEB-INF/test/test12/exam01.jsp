<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../../include/inc_menu.jsp"  %>
<h2>test12</h2>


	<c:set var="k" value="${k = 0 }"></c:set>
	<c:forEach items="${list }" var="list">
	<input type="text" name="box_${k }" id="box_${k }" value="${list }" style="width: 250px;">
	${list }&nbsp;
	<a href="#" onclick="sakjae('box_${k }');">[삭제]</a>
	<br>
	<c:set var="k" value="${k = k+1 }"></c:set>
</c:forEach>
	
<hr>	
<form name="imsiForm">
<input type="hidden"  name="filePath" value="" style="width: 300px">
 </form>

<script type="text/javascript">
	function sakjae(value1 ) {
		var imsi = document.getElementById(value1).value;
		
		document.imsiForm.filePath.value = imsi;
		
		if(confirm('삭제하시겠습니까?')){
			document.imsiForm.method = 'post';
			document.imsiForm.action = '${path}/test_servlet/test12Proc.do';
			document.imsiForm.submit();
		}
		
	}


</script>


</body>
</html>
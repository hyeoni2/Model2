<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
<%@include file="../include/inc_menu.jsp"  %>

<h2> 성적 수정 </h2>
<c:forEach var="dto" items="${ list}">
<form name="sujungForm">
	<input type="hidden" name="no" value="${dto.no }">
	<table border="1">
		<tr>
			<td>아이디  </td>
			<td>
				${dto.id }
			</td>
		</tr>
		<tr>
			<td>이름  </td>
			<td>
				<input type="text" name="name" value="${dto.name }">
			</td>
		</tr>
		<tr>
			<td>시험명  </td>
			<td>
				<input type="text" name="sihum_name" value="${dto.sihum_name }">
			</td>
		</tr>
		
		<tr>
			<td>국어  </td>
				<td>
				<input type="text" name="kor" value="${dto.kor }">
			</td>
		</tr>
		<tr>
			<td>영어  </td>
			<td>
				<input type="text" name="eng" value="${dto.eng }">
			</td>
		</tr>
		<tr>
			<td>수학  </td>
			<td>
				<input type="text" name="mat" value="${dto.mat }">
			</td>
		</tr>
		<tr>
			<td>과학  </td>
			<td>
				<input type="text" name="sci" value="${dto.sci }">
			</td>
		</tr>
		<tr>
			<td>역사  </td>
			<td>
				<input type="text" name="his" value="${dto.his }">
			</td>
		</tr>
		<tr>
			<td>등록일  </td>
			<td>
				${dto.regi_date }
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="sujung();">수정하기</button>
				<button type="button" onclick="list();">목록으로</button>
			</td>
		</tr>
	
	</table>
</form>
</c:forEach>
</body>
<script type="text/javascript">

function list() {
	location.href = '${path}/sungjuk_servlet/List.do';
}

function sujung() {
	
	if(confirm('수정하시겠습니까?')){
		sujungForm.method = 'post';
		sujungForm.action = '${path}/sungjuk_servlet/SujungProc.do';
		sujungForm.submit();
	}
		
	
}


</script>
</html>
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

<h2> 성적 삭제</h2>
<c:forEach var="dto" items="${ list}">
<form name="sakjaeForm">
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
			${dto.name }
			</td>
		</tr>
		<tr>
			<td>시험명  </td>
			<td>
				${dto.sihum_name }
			</td>
		</tr>
		
		<tr>
			<td>국어  </td>
				<td>
				${dto.kor }
			</td>
		</tr>
		<tr>
			<td>영어  </td>
			<td>
				${dto.eng }
			</td>
		</tr>
		<tr>
			<td>수학  </td>
			<td>
				${dto.mat }
			</td>
		</tr>
		<tr>
			<td>과학  </td>
			<td>
				${dto.sci }
			</td>
		</tr>
		<tr>
			<td>역사  </td>
			<td>
				${dto.his }
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="sakjae();">삭제하기</button>
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

function sakjae() {
	
	if(confirm('삭제하시겠습니까?')){
		sakjaeForm.method = 'post';
		sakjaeForm.action = '${path}/sungjuk_servlet/SakjaeProc.do';
		sakjaeForm.submit();
	}
		
	
}


</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<%@include file="../include/inc_menu.jsp"  %>
<h2>게시판 수정</h2>
	<table width = 80%>
		<!-- 첫번쨰열 -->
		<tr>
			<td>
<form name="sujungForm">
<c:set var="dto" value="${dto }"></c:set>
	<input type="hidden" value="${dto.no }" name="no">
	<table border="1" width = 50%>
						<tr>
							<td>
								작성자 / 이메일
							</td>
							<td>
								${dto.writer }(${dto.email })
							</td>
						</tr>
						<tr>
							<td>
								제목
							</td>
							<td>
								<input type="text" name="subject" value="${dto.subject }"> 
							</td>
						</tr>
						<tr>
							<td>
								내용
							</td>
							<td>
								<textarea style="width: 170px;" name="content" id="content">${dto.content }</textarea>
							</td>
						</tr>
						<tr>
							<td>
								등록일
							</td>
							<td>
								${dto.regi_date }
							</td>
							
						</tr>	
						<tr>
							<td>
								비밀번호
							</td>
							<td>
								<input type="password" name="passwd" > 
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
				</td>
			</tr>

		<tr>
			<td>
			

			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	
function list() {
	location.href = '${path}/boardBasicMybatis_servlet/List.do';
}

function sujung() {
	
	if(confirm('수정하시겠습니까?')){
		sujungForm.action = '${path}/boardBasicMybatis_servlet/SujungProc.do';
		sujungForm.method = 'post';
		sujungForm.submit();
	}	
}
</script>
</html>
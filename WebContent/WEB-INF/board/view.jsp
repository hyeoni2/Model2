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
<h2>게시판 상세 목록</h2>
	<table width = 80%>
		<!-- 첫번쨰열 -->
		<tr>
			<td>
				<span>추천수</span>
				<input type="text" size="5" id="chu" readonly>
				<button type="button" onclick="chuchun();">추천</button>

<c:set var="dto" value="${dto }"></c:set>
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
								${dto.subject }
							</td>
						</tr>
						<tr>
							<td>
								내용
							</td>
							<td>
								${dto.content }
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
					</table>
				|
				<a href = "${path}/board_servlet/List.do">목록</a>
				|
				<a href = "${path}/board_servlet/Chuga.do?no=${dto.no}">답변</a>
				|
				</td>
			</tr>

		<tr>
			<td>
			

			</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
	
	i = $("#chu").val();
	function chuchun() {
		i++;
		$("#chu").val(i);
	}
	
	function chuga() {
		
		if(confirm('댓글을 등록하시겠습니까?')){
			
			re_chugaForm.method = 'post';
			re_chugaForm.action = '${path}/board_servlet/RechugaProc.do'
			re_chugaForm.submit();
		
			
		
	}
}			
</script>
</html>
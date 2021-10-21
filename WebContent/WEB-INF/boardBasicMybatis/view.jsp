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
				<a href = "${path}/boardBasicMybatis_servlet/List.do">목록</a>
				|
				<a href = "${path}/boardBasicMybatis_servlet/Chuga.do?no=${dto.no}">답변</a>
				|			
				<a href = "${path}/boardBasicMybatis_servlet/Sujung.do?no=${dto.no}">수정</a>
				|
				<a href = "${path}/boardBasicMybatis_servlet/Sakjae.do?no=${dto.no}">삭제</a>
				|
				</td>
			</tr>

		<tr>
			<td>
			

			</td>
		</tr>
	</table>
</body>

</html>
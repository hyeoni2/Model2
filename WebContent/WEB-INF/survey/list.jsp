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
<h2>설문조사 목록</h2>
<table border="1">
	<tr>
		<th>순번</th>
		<th>질문</th>
		<th>답1</th>
		<th>답2</th>
		<th>답3</th>
		<th>답4</th>
		<th>기간</th>
		<th>참여수</th>
		<th>상태</th>
		<th>등록일</th>
	</tr>


 <c:forEach var="dto" items="${list }" >
	
	<tr>
		<td>
				${dto.no }
		</td>
	
		<td>
			<a href="${path }/survey_servlet/View.do?no=${dto.no}">
				${dto.question }
			</a>
		</td>
	
		<td>
			${dto.ans1 }
		</td>
	
		<td>
				${dto.ans2 }
		</td>
	
	
		<td>
				${dto.ans3 }
		</td>
	
		<td>
				${dto.ans4 }
		</td>
		<td>
				${dto.start_date } ~ ${dto.last_date }
		</td>
		<td>
	
				${dto.count }

		</td>
		<td>
				${dto.status }
		</td>
		<td>
				${dto.regi_date }
		</td>
	
	</tr>
	</c:forEach>

<c:if test="${fn:length(list ) == 0}">
	<tr>
		<td colspan="10">
			등록된 내용이 없습니다.
		</td>
	</tr>
	</c:if>		
</table>
<br>

|
<a href = "${path}/survey_servlet/Chuga.do">등록</a>
|
<a href = "${path}/survey_servlet/Munjae.do">문제풀이</a>
|

<c:if test="${sessionScope.cookAbility eq 'A' || sessionScope.cookAbility eq 'B' }">
</c:if>
</body>
</html>
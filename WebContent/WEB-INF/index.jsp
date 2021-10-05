<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="./include/inc_menu.jsp" %>
<h2>index</h2>

<c:choose>
	<c:when test="${sessionScope.cookNor == null || sessionScope.cookNor == 0}">
		<table border="1" width = 60%>
			<tr>	
				<td  align="center">
					<font style="font-size: 60px; font-weight: bold;">
					Here<br>we<br>Go!!!
					</font>
				</td>
			</tr>
			<tr>
				<td align="center" style="padding: 10px 0px;">
					<form name="loginForm" method="post" action="${path }/member_servlet/memberLoginProc.do">
					아이디 : <input type="text" name="id" style="width: 100px;">
					비밀번호 : <input type="text" name="passwd" style="width: 100px;" >
					<button type="submit">로그인</button>
					</form>
				</td>
			</tr>
		</table>
		
	</c:when>
	<c:otherwise>
	
		<h2>DashBoard</h2>
	
		<table border="1" width = 60%>
	
			<tr>
				<td align="center" style="width: 30px; height: 200px;">
					ddd
					
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>


</body>
</html>
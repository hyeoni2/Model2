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
<h2>상품 목록</h2>
<table border="1">
	<tr>
		<th>순번</th>
		<th>상품 이름</th>
		<th>상품 이미지</th>
		<th>상품 파일명</th>
		<th>상품 가격</th>
		<th>등록일</th>
	</tr>


 <c:forEach var="dto" items="${list }" >
	
	<tr>
		<td>
				${dto.no }
		</td>
		
		<td>
			<a href="${path }/product_servlet/productView.do?no=${dto.no}">
				${dto.name }
			</a>
		</td>
	
		<td>
			<c:choose>
				<c:when test="${dto.product_img == '-' }">
					이미지 없음
				</c:when>
				<c:otherwise>
					<img src="${path }/attach/product_img/${dto.product_img}" width="100" height="70">
				</c:otherwise>
			</c:choose>
		
		
		</td>
		<td>
				${dto.product_img }<br>
				${dto.product_img_original}
		</td>
	
	
		<td>
				${dto.price }
		</td>
	
		<td>
				${dto.regi_date }
		</td>
	
	</tr>
	
</c:forEach>	
<c:if test="${fn:length(list ) == 0}">
	<tr>
		<td colspan="6" > 
			등록된 내용이 없습니다.
		</td>
	</tr>
	</c:if>		
</table>
<br>
<c:if test="${sessionScope.cookAbility eq 'A' || sessionScope.cookAbility eq 'B' }">
	|
	<a href = "${path}/product_servlet/productChuga.do">등록</a>
	|
</c:if>
</body>
</html>
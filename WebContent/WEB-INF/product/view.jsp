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
<h2>상품 상세 정보</h2>
<c:forEach items="${list }" var="dto">
<table border="1">
		<tr>
			<td>
				상품 이름
			</td>
	
			<td>
				${dto.name }
			</td>
		</tr>
		<tr>
			<td>
				상품 가격
			</td>
	
			<td>
				${dto.price}
			</td>
		</tr>
		<tr>
			<td>
				상품 이미지
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
		</tr>
		<tr>
			<td>
				상품 파일명
			</td>
	
			<td>
				${dto.product_img } / ${dto.product_img_original }
			</td>
		</tr>
		<tr>
			<td>
				상품 설명
			</td>
	
			<td>
				${dto.description }
			</td>
		</tr>
		<tr>
			<td>
				등록일
			</td>
	
			<td>
				${dto.regi_date}
			</td>
		</tr>

		
</table>
<%-- 
|
<a href="${path }/product_servlet/productChuga.do">등록</a>
|
<a href="${path }/product_servlet/productSujung.do?product_code=${dto.product_code}">수정</a>
|
<a href="${path }/product_servlet/productList.do">목록</a>
|
<a href="${path }/product_servlet/productSakjae.do?product_code=${dto.product_code}">삭제</a>
|
 --%>

<c:choose>
	<c:when test="${sessionScope.cookAbility eq 'A'}">
		|
		<a href="${path }/product_servlet/productChuga.do">등록</a>
		|
		
		<a href="${path }/product_servlet/productSujung.do?no=${dto.no}">수정</a>
		|
		
		<a href="${path }/product_servlet/productList.do">목록</a>
		|
		<a href="${path }/product_servlet/productSakjae.do?no=${dto.no}">삭제</a>
		|
	</c:when>	
	<c:when test="${sessionScope.cookAbility eq 'B'}">
		|
		<a href="${path }/product_servlet/productChuga.do">등록</a>
		|
		
		<a href="${path }/product_servlet/productSujung.do?no=${dto.no}">수정</a>
		|
		<a href="${path }/product_servlet/productList.do">목록</a>
		|
	</c:when>
	<c:otherwise>
		|
		<a href="${path }/product_servlet/productList.do">목록</a>
		|
	</c:otherwise>
</c:choose>

</c:forEach>
</body>
</html>
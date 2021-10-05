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
<h2>회원 수정</h2>
<c:forEach items="${list }" var="dto">
	<form name = "sujungForm">	
			<input type="hidden" name="no" value="${dto.no }">
		<table border="1" width = 50%>	
				<tr>
					<td>상품 이름</td>
					<td><input type="text" name="name" value="${dto.name }"></td>
				</tr>
				<tr>
					<td>상품 가격</td>
					<td><input type="text" name="price" value="${dto.price }"></td>
				</tr>
				<tr>
					<td>상품 이미지</td>
					<td>
						
						<c:choose>
								<c:when test="${dto.product_img == '-' }">
									이미지 없음
								</c:when>
								<c:otherwise>
									<img src="${path }/attach/product_img/${dto.product_img}"  name="product_img"  width="100" height="70">
								</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>바꿀 이미지</td>
					<td>
						<input type="file" name="product_img" value="${dto.product_img }" >
						
					</td>
				</tr>
				<tr>
					<td>상품 설명</td>
					<td><input type="text" name="description" value="${dto.description }"></td>
				</tr>
				<tr>
					<td>
						등록일
					</td>
			
					<td>
						${dto.regi_date}
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


</html>


<script>
	function list() {
		location.href = '${path}/product_servlet/productList.do';
	}

	function sujung() {
		sujungForm.enctype = "multipart/form-data";
		sujungForm.method = 'post';
		sujungForm.action = '${path}/product_servlet/productSujungProc.do';
		sujungForm.submit();
	}
</script>


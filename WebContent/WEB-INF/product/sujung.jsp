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
<c:set var="main" value="${fn:split(dto.product_img,',')[0]}"></c:set>
<c:set var="gita1" value="${fn:split(dto.product_img,',')[1]}"></c:set>
<c:set var="gita2" value="${fn:split(dto.product_img,',')[2]}"></c:set>

	<form name = "sujungForm">	
			<input type="hidden" name="no" value="${dto.no }">
		<table border="1" >	
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
							<c:when test="${main == '-' }">
								이미지 없음
							</c:when>
				
							<c:otherwise>
								<c:choose>
									<c:when test="${gita1 == '-'  && gita2 == '-'}">
										<img src="${path }/attach/product_img/${main}" width="100" height="70">
									</c:when>
									<c:when test="${gita1 == '-'  && gita2 != '-'}">
										<img src="${path }/attach/product_img/${main}" width="100" height="70">
										<img src="${path }/attach/product_img/${gita2}" width="100" height="70">
									</c:when>
									<c:when test="${gita1 != '-'  && gita2 == '-'}">
										<img src="${path }/attach/product_img/${main}" width="100" height="70">
										<img src="${path }/attach/product_img/${gita1}" width="100" height="70">
									</c:when>
								
									<c:otherwise>						
										<img src="${path }/attach/product_img/${main}" width="100" height="70">
										<img src="${path }/attach/product_img/${gita1}" width="100" height="70">
										<img src="${path }/attach/product_img/${gita2}" width="100" height="70">
									</c:otherwise>
								</c:choose>
								
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td>바꿀 메인 이미지</td>
					<td>
							<input type="file" name="0" id="0" class="fileUp">
						
					</td>
				</tr>
				<tr>
					<td>바꿀 기타 이미지1</td>
					<td>
							<input type="file" name="1" id="1" class="fileUp">
					</td>
				</tr>
				<tr>		
					<td>바꿀 기타 이미지2</td>
					<td>
							<input type="file" name="2" id="2" class="fileUp">
						
							<input type="hidden" name="count">
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
		location.href = '${path}/product_servlet/List.do';
	}

	function sujung() {
		sujungForm.count.value = sujungForm.getElementsByClassName("fileUp").length;
		sujungForm.enctype = "multipart/form-data";
		sujungForm.method = 'post';
		sujungForm.action = '${path}/product_servlet/SujungProc.do';
		sujungForm.submit();
	}
</script>


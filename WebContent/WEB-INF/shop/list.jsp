<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../include/inc_menu.jsp" %>
<table border="0"  style="width: 80%; margin: auto;" >

	<tr>		
		<td>
			<h2>ShopingMall</h2>
		</td>
	</tr>

</table>
	
<table border="1"  style="width: 80%; margin: auto;" >
	<tr>
	
			<c:set var="i" value="${i = 0 }"></c:set>
			<c:set var="num" value="${num = 3 }"></c:set>
				
				
				<c:forEach var="dto" items="${list }" >
				<c:set var="main" value="${fn:split(dto.product_img,',')[0]}"></c:set>
				
				<c:set var="j" value="${fn:length(list) }"></c:set>
			<c:choose>
					<c:when test="${i % num != 0 }">
								<td align="center" style="height: 300px">
									 	
									 	<table border="1" style="width: 200px; height: 200px; text-align: center;">
													<tr>
														<td colspan="2" align="center">
															<a href="${path }/shop_servlet/View.do?no=${dto.no}">
																	<c:choose>
																			<c:when test="${main == '-' }">
																				메인이미지 없음
																			</c:when>
																			<c:otherwise>
																				<img src="${path }/attach/product_img/${main}" width="100" height="70">
																			</c:otherwise>
																	</c:choose>			
															</a>
														</td>
					
													</tr>
													<tr>	
														<td>
															상품명
														</td>
														<td>
															${dto.name}
														</td>
													</tr>
													<tr>	
														<td>
															상품가격
														</td>
														<td>
														<fmt:formatNumber value="${dto.price }" pattern="#,###,###"/>
														</td>
													</tr>					
										</table>	
												
								</td>	
						</c:when>
	
	
			<c:otherwise>
							<tr>
								<td align="center" style="height: 300px">
								 	
									 	<table border="1" style="width: 200px; height: 200px; text-align: center;">
													<tr>
														<td colspan="2" align="center">
															<a href="${path }/shop_servlet/View.do?no=${dto.no}">
																<img src="${path }/attach/product_img/${main}" width="100px" height="100px">
															</a>
														</td>
													</tr>
													<tr>	
														<td>
															상품명
														</td>
														<td>
															${dto.name}
														</td>
													</tr>
													<tr>	
														<td>
															상품가격
														</td>
														<td>
															<fmt:formatNumber value="${dto.price }" pattern="#,###,###"/>
														</td>
													</tr>
										</table>			
									</td>
				</c:otherwise>
			</c:choose>
					<c:set var="i" value="${i = i + 1 }"></c:set>
				</c:forEach>		
					<c:forEach begin="1" end="${num-(fn:length(list)%num) }">
						<c:choose>
							<c:when test="${i % num != 0 }">
								<td>
								</td>
							</c:when>
						</c:choose>
					</c:forEach>
			
	</tr>	
</table>

<table border="0"  style="width: 80%; margin: auto;" >	
	<tr>
		<td colspan="3"  align="right">
			<button type="button" onclick="list();">상품목록</button>	
			<button type="button" onclick="buyPro();">장바구니</button>	
		</td>
	</tr>
</table>		

<script type="text/javascript">
	function buyPro() {
		location.href = '${path}/shop_servlet/Buy.do';
	}

	function list() {
		location.href = '${path}/product_servlet/productList.do';
	}

</script>

</body>


</html>
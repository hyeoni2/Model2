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
<c:forEach var="dto" items="${list }">
<c:set var="main" value="${fn:split(dto.product_img,',')[0]}"></c:set>
<c:set var="gita1" value="${fn:split(dto.product_img,',')[1]}"></c:set>
<c:set var="gita2" value="${fn:split(dto.product_img,',')[2]}"></c:set>
<h2>${dto.name}</h2>
	<form name="buyForm">
		<input type="hidden" name="no" value="${dto.no }">
<table border="1" width = 80%;>
						<tr>
							<td>
								상품명		
							</td>
							<td>
								${dto.name }		
							</td>
						</tr>
						<tr>
							<td>
								상품 이미지	
							</td>
								<td>
									<c:choose>
												<c:when test="${main == '-' && gita1 == '-'  && gita2 == '-'}">
											이미지 없음
										</c:when>
										<c:when test="${main != '-' && gita1 == '-'  && gita2 == '-'}">
											<img src="${path }/attach/product_img/${main}" width="100" height="70">
										</c:when>
										<c:when test="${main != '-' && gita1 == '-'  && gita2 != '-'}">
											<img src="${path }/attach/product_img/${main}" width="100" height="70">
											<img src="${path }/attach/product_img/${gita2}" width="100" height="70">
										</c:when>
										<c:when test="${main != '-' && gita1 != '-'  && gita2 == '-'}">
											<img src="${path }/attach/product_img/${main}" width="100" height="70">
											<img src="${path }/attach/product_img/${gita1}" width="100" height="70">
										</c:when>
									
										<c:when test="${main == '-' && gita1 != '-'  && gita2 == '-'}">
											<img src="${path }/attach/product_img/${gita1}" width="100" height="70">
										</c:when>
										
										<c:when test="${main == '-' && gita1 == '-'  && gita2 != '-'}">
											<img src="${path }/attach/product_img/${gita2}" width="100" height="70">
										</c:when>
									
										<c:otherwise>						
											<img src="${path }/attach/product_img/${main}" width="100" height="70">
											<img src="${path }/attach/product_img/${gita1}" width="100" height="70">
											<img src="${path }/attach/product_img/${gita2}" width="100" height="70">
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>
								상품 가격		
							</td>
							<td>
								<fmt:formatNumber value="${dto.price }" pattern="#,###,###"/>
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
								${dto.regi_date }		
							</td>
						</tr>
	<tr>
		<td colspan="2" align="right">
			수량 : 
			<button type="button" onclick="amountPro('minus');">-</button>
			<input type="text" size="2px" value="0" id="amount" name="amount" style="text-align: center;">
			<button type="button" onclick="amountPro('plus');">+</button>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<button type="button" onclick="list();">쇼핑몰로</button>
			<button type="button" onclick="buyPro();">장바구니</button>
		</td>
	</tr>
	</table>
	</form>		

<script type="text/javascript">

	amount = buyForm.amount.value;
	
	function list() {
		location.href = '${path}/shop_servlet/List.do';
	}
	
	function buyPro() {
		if(confirm("${dto.name} "+ amount+"개 담으시겠습니까?")){		
			buyForm.method = 'post';	
			buyForm.action = '${path}/shop_servlet/ViewProc.do';	
			buyForm.submit();
		}
	}
		
	function amountPro(value1) {
		
		amount = buyForm.amount.value;
		
		if(value1 == "plus"){
			amount = parseInt(amount) +1;
		}else if(value1 == "minus"){
			if(amount == 0){
				return;
			}
			amount = parseInt(amount) -1;
			
		}
		
		buyForm.amount.value = amount;

	}
</script>

</c:forEach>
</body>
</html>
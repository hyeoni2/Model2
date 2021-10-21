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
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<h2>장바구니</h2>


<form name="clearForm">
	<table border="1" style="margin: auto; text-align: center;" width = 90%>
	<tr>
			<td width="50px;">
				<input type="checkbox" name="check" id="checkAll">
			</td>
			
			<td width="150px;">
				이미지
			</td>
			
			<td width="150px"> 
				회원명/번호
			</td>
			<td width="150px"> 
				상품 이름
			</td>
			<td width="50px"> 
				수량
			</td>
			<td width="150px">
				상품 단가
			</td>	
			<td width="150px">
				가격
			</td>
			<td width="150px">
				비고
			</td>
	</tr>				
		<c:forEach var="dto" items="${list }">	
		<c:set var="main" value="${fn:split(dto.product_img,',')[0]}"></c:set>
	<tr>			
		
		<td align="center"> 
			<input type="checkbox" name="no" id="chk" value ="${dto.no }">${dto.no }
		</td>
	
		<td> 

			<c:choose>
				<c:when test="${main != '-' }">
					<img src="${path }/attach/product_img/${main}" width="150px;" height="100px;">
				</c:when>
				<c:otherwise>
					메인이미지 없음
				</c:otherwise>
			</c:choose>	
			
		</td>
	
	
		<td> 
			${dto.memberName }/
			[${dto.memberNo }]
		</td>
	
	
		<td> 
			${dto.product_name }
		</td>
	
		<td> 
			${dto.amount }
		</td>
	
		<td> 
			<fmt:formatNumber pattern="###,###,###" value="${dto.price }">
			</fmt:formatNumber>
			
		</td>
	
	
		<td> 
			<fmt:formatNumber pattern="###,###,###" value="${(dto.amount * dto.price) }">
			</fmt:formatNumber>
			
		</td>
		<td>
			<a href="#" onclick="cartClearOne('${dto.no}');">[삭제]</a>
			<br>
			<a href="#" onclick="cartClearOneForm('${dto.no}', '${dto.product_name }');">[삭제]<post></a>
		</td>
	</tr>
		</c:forEach>
	<c:if test="${fn:length(list) ==  0}">
		<tr>			
					<td colspan="8">
							장바구니가 비어있습니다.
					</td>		
		</tr>
	</c:if>
	<tr>
		<td colspan="8" align="right">
			합계 금액 : 
			<fmt:formatNumber pattern="###,###,###" value="${(sum) }">
			</fmt:formatNumber>
		</td>
	</tr>
	<tr>
		<td colspan="8" align="right ">
			<button type="button" onclick="cartClear();">장바구니 비우기</button>
			<button type="button" onclick="list();">쇼핑하기</button>
			<button type="button" onclick="order();">주문하기</button>
		</td>
	</tr>
	</table>
</form>		
<br>
<form name="baguniForm">
	<input type="hidden" name="no" id="no" ><br>
	<input type="hidden" name="name" id="arg">	
	<input type="hidden" name="nums" id="sunteckNo">	
</form>

</body>

<script type="text/javascript">

$(document).ready(function () {
	
	$("#checkAll").click(function () {

		if($("#checkAll").prop("checked")){
			$("input[name=no]").prop("checked", true);
		}else{
			$("input[name=no]").prop("checked", false);
		}
		
	});
});


function cartClearOne(value1) {
	if(confirm('선택한 상품을 삭제하시겠습니까?')){
		location.href = "${path}/shop_servlet/cartClearOne.do?no="+value1;
	}	
}



function cartClearOneForm(value1, value2){
	
	$("#no").val(value1);
	$("#arg").val(value2);
	
	if(confirm('선택한 상품을 삭제하시겠습니까?')){
		baguniForm.method = 'post';
		baguniForm.action = '${path}/shop_servlet/cartClearOne.do';
		baguniForm.submit();
		
	}
}

function list() {
	location.href = "${path}/shop_servlet/List.do";
}



function cartClear() {
	
	if(confirm('장바구니를 비우시겠습니까?')){
		clearForm.action = "${path}/shop_servlet/cartClear.do";
		clearForm.method = "post";
		clearForm.submit();
	}
}


</script>
</html>

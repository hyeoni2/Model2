<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

$(function() {
	/* alert('추가페이지'); */
	$('#question').focus();
});
</script>

</head>
<body>
<%@include file="../include/inc_menu.jsp"  %>
<h2>문제 등록</h2>
<form name = "chugaForm">	
	<table>
		<tr>
			<td>문제</td>
			<td><input type="text" name="question" id="question"></td>
		</tr>
		<tr>
			<td>답변 1</td>
			<td>
				<input type="text" name="ans1" id="ans1" >
			</td>
		</tr>
		<tr>
			<td>답변 2</td>
			<td>
				<input type="text" name="ans2" id="ans2" >
			</td>
		</tr>
		<tr>
			<td>답변 3</td>
			<td>
				<input type="text" name="ans3" id="ans3">
			</td>
		</tr>
		<tr>
			<td>답변 4</td>
			<td>
				<input type="text" name="ans4" id="ans4" >
			</td>
		</tr>
		<tr>
			<td>정답</td>
			<td>
				<input type="text" name="real_answer" id="real_answer" >
			</td>
		</tr>
		<tr>
			<td>진행상태</td>
			<td>
				<input type="radio" name="status" value="1" checked>진행중
				<input type="radio" name="status"  value="0">종료
			</td>
		</tr>
		<tr>
			<td align="center">시작날짜</td>
			<td>
				<select name="syear" id="syear">
					<c:forEach var="i" begin="${naljaMap['now_y']-1}" end="${naljaMap['now_y']+1}" step="1">
						<c:if test="${naljaMap['now_y'] == i}">
							<option value="${i}" selected>${i }</option>
						</c:if>
						<c:if test="${naljaMap['now_y'] != i}">
							<option value="${i}" >${i }</option>
						</c:if>						
					</c:forEach>
				</select>년
				
				<select name="smonth" id="smonth">
					<c:forEach var="i" begin="1" end="12" step="1">
						<c:if test="${naljaMap['now_m'] == i}">
							<c:if test="${i < 10 } ">					
								<option value="0${i}" selected>0${i }</option>
							</c:if>	
							<c:if test="${i >= 10 }">					
								<option value="${i}" selected>${i }</option>
							</c:if>	
						</c:if>
						
						<c:if test="${naljaMap['now_m'] != i}">
							<c:if test="${i < 10 } ">					
								<option value="0${i}" >0${i }</option>
							</c:if>	
							<c:if test="${i >= 10 } ">					
								<option value="${i}" >${i }</option>
							</c:if>
						</c:if>				
						
					</c:forEach>
				</select>월
				
				<select name="sday" id="sday">
					<c:forEach var="i" begin="1" end="31" step="1">
						<c:if test="${naljaMap['now_d'] == i}">
							<c:if test="${i < 10 } ">					
								<option value="0${i}" selected>0${i }</option>
							</c:if>	
							<c:if test="${i >= 10 }">					
								<option value="${i}" selected>${i }</option>
							</c:if>	
						</c:if>
						
						<c:if test="${naljaMap['now_d'] != i}">
							<c:if test="${i < 10 } ">					
								<option value="0${i}" >0${i }</option>
							</c:if>	
							<c:if test="${i >= 10 } ">					
								<option value="${i}" >${i }</option>
							</c:if>
						</c:if>				
						
					</c:forEach>
				</select>일
				
			</td>
		</tr>
		<tr>
			<td>종료날짜</td>
			<td>
				<select name="lyear" id="lyear">
					<c:forEach var="i" begin="${naljaMap['now_y']-1}" end="${naljaMap['now_y']+1}" step="1">
						<c:if test="${naljaMap['now_y'] == i}">
							<option value="${i}" selected>${i }</option>
						</c:if>
						<c:if test="${naljaMap['now_y'] != i}">
							<option value="${i}" >${i }</option>
						</c:if>						
					</c:forEach>
				</select>년
				
				<select name="lmonth" id="lmonth">
					<c:forEach var="i" begin="1" end="12" step="1">
					<c:choose>
						<c:when test="${naljaMap['now_m'] == i}">
							<c:choose>
								<c:when test="${i < 10 }">
									<option value="0${i}" selected>0${i }</option>								
								</c:when>
								<c:otherwise>										
									<option value="${i}" selected>${i }</option>								
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${naljaMap['now_m'] != i}">
							<c:choose>
								<c:when test="${i < 10 }">
									<option value="0${i}" >0${i }</option>								
								</c:when>
								<c:otherwise>										
									<option value="${i}" >${i }</option>								
								</c:otherwise>
							</c:choose>
						</c:when>							
					</c:choose>
					</c:forEach>
				</select>월
				
			<select name="lday" id="lday">
					<c:forEach var="i" begin="1" end="31" step="1">			
						
							<c:choose>
								<c:when test="${naljaMap['now_d'] == i}">
									<c:choose>
										<c:when test="${i < 10 }">
											<option value="0${i}" selected>0${i }</option>								
										</c:when>
										<c:otherwise>										
											<option value="${i}" selected>${i }</option>								
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${naljaMap['now_d'] != i}">
										<c:choose>
										<c:when test="${i < 10 }">
											<option value="0${i}" >0${i }</option>								
										</c:when>
										<c:otherwise>										
											<option value="${i}" >${i }</option>								
										</c:otherwise>
									</c:choose>
								</c:when>
							</c:choose>		
						</c:forEach>
				</select>일
				
			</td>
		</tr>

		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="chuga();">등록하기</button>
				<button type="button" onclick="list();">목록으로</button>
			</td>
		</tr>
	</table>
</form>	
</body>


</html>


<script>

	
	function list() {
		location.href = '${path}/survey_servlet/List.do';
	}

	function chuga() {

		
		if(chugaForm.question.value == ""){
			alert('질문을 입력해주세요');
			chugaForm.question.focus();
			return;
		}
		
		if(chugaForm.ans1.value == ""){
			alert('답변 1번을 입력해주세요');
			chugaForm.ans1.focus();
			return;
		}
		if(chugaForm.ans2.value == ""){
			alert('답변 2번을 입력해주세요');
			chugaForm.ans2.focus();
			return;
		}
		if(chugaForm.ans3.value == ""){
			alert('답변 3번을 입력해주세요');
			chugaForm.ans3.focus();
			return;
		}
		if(chugaForm.ans4.value == ""){
			alert('답변 4번을 입력해주세요');
			chugaForm.ans4.focus();
			return;
		}
	
		if(chugaForm.real_answer.value == ""){
			alert('정답을 입력해주세요');
			chugaForm.real_answer.focus();
			return;
		}
		

		
		if(confirm('등록하시겠습니까?')){
			chugaForm.action = '${path}/survey_servlet/ChugaProc.do';
			chugaForm.method = 'post';
			chugaForm.submit();
		}	
	}
</script>


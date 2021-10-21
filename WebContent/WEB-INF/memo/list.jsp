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
<h2>메모 목록</h2>
<span id="click" onclick="clickPro();">[등록]</span>
<div id="hidePro" style="display: none">
	<form name="saveForm">
	<span id="gubun" style="display: none ;"></span>
	<input type="hidden" name = "no"  id="no" value="">
		<table>
			<tr>
				<td>작성자</td>
				<td><input type="text" name="writer" id="writer"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" id="content" style="width: 150px; height: 30px"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnSave" onclick="submitProc();">등록하기</button>
	
					<button type="button" id="btnClear" onclick="clearForm();" style="display: none;">초기화</button>
					
				</td>
			</tr>
		</table>		
	</form>
</div>
<script type="text/javascript">

	function clickPro() {
		$("#hidePro").show();	
		$("#click").hide();
		
	}

	function change(value1 , value2, value3) {

		$("#gubun").text("M");
		$("#no").val(value1);
		$("#writer").val(value2);
		$("#content").val(value3);

		$("#btnSave").text("수정하기");
		$("#btnClear").show();
		$("#hidePro").show();	
		$("#click").hide();
		
	}
	
	function clearForm() {
		
		$("#gubun").text("");
		$("#no").val("");
		$("#writer").val("");
		$("#content").val("");
		
		$("#btnSave").text("등록하기");
		$("#btnClear").hide();
		
	}
	
	function erase(value1, value2, value3) {
		
		$("#gubun").text("D");
		$("#no").val(value1);
		$("#writer").val(value2);
		
		$("#content").val(value3);
		
		$("#btnSave").text("삭제하기");
		$("#btnClear").show();
		$("#hidePro").show();	
		$("#click").hide();
		
	}
	
	function submitProc(value1) {
		
		var value1 = $("#gubun").text();
		
		if($("#writer").val() == ""){
			alert('작성자를 입력해주세요');
			$("#writer").focus();
			return;
		}
		
		if($("#content").val() == ""){
			alert('내용을 입력해주세요');
			$("#content").focus();
			return;
		}
			

		if(value1 == "M"){	
			url = '${path}/memo_servlet/SujungProc.do';
			
		}else if(value1 == "D"){	
			url = '${path}/memo_servlet/SakjaeProc.do';
			
		}else{
			url = '${path}/memo_servlet/ChugaProc.do';
		}
		
		
			document.saveForm.method = 'post';
			document.saveForm.action = url;
			document.saveForm.submit();
		
	}

</script>




<hr>
<table border="1">
	<tr>
		<th>순번</th>
		<th>작성자</th>
		<th>내용</th>
		<th>등록일</th>
		<th>비고</th>
	</tr>


 <c:forEach var="dto" items="${list }" >
	
	<tr>
		<td>
				${dto.no }
		</td>
	
		<td>
			<a href="${path }/memo_servlet/View.do?no=${dto.no}">
				${dto.writer }
			</a>
		</td>
	
	
		<td>
				${dto.content }
		</td>
		
		 <td>
				${dto.regi_date }
		</td>
	
		<td>
			<a href="#" onclick="change('${dto.no}','${dto.writer }', '${dto.content }' );">수정</a>
			/
			<a href="#" onclick="erase('${dto.no}','${dto.writer }', '${dto.content }' );">삭제</a>
		</td>
	</tr>
	
	
</c:forEach>	
<c:if test="${fn:length(list ) == 0}">
	<tr>
		<td colspan="5" > 
			등록된 내용이 없습니다.
		</td>
	</tr>
	</c:if>		
</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="../../include/inc_menu.jsp" %>
<h2>test09Result</h2>

이름 : ${map.dto.name }<br>
국어 : ${map.dto.kor }<br>
영어 : ${map.dto.eng }<br>
수학 : ${map.dto.mat }<br>
사회 : ${map.dto.soc }<br>
과학 : ${map.dto.sci }<br>
역사 : ${map.dto.his }<br>
총 점 : ${map.dto.tot }<br>
평균 : ${map.dto.avg }<br>
등급 : ${map.dto.grade }<br>

<%-- 
이름 : ${dto.name }<br>
국어 : ${dto.kor }<br>
영어 : ${dto.eng }<br>
수학 : ${dto.mat }<br>
사회 : ${dto.soc }<br>
과학 : ${dto.sci }<br>
역사 : ${dto.his }<br>
총 점 : ${dto.tot }<br>
평균 : ${dto.avg }<br>
등급 : ${dto.grade }<br> 
--%>

</body>
</html>
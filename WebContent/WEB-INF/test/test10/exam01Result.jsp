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
<h2>test10Result</h2>

이름 : ${ map.name }<br>
국어 : ${ map.kor }<br>
영어 : ${ map.eng }<br>
수학 : ${ map.mat }<br>
사회 : ${ map.soc }<br>
과학 : ${ map.sci }<br>
역사 : ${ map.his }<br>
총 점 : ${ map.tot }<br>
평균 : ${ map.avg }<br>
등급 : ${ map.grade }<br>


</body>
</html>
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
<h2>test05Result</h2>

이름 : ${name }<br>
국어 : ${kor }<br>
영어 : ${eng }<br>
수학 : ${mat }<br>
사회 : ${soc }<br>
과학 : ${sci }<br>
역사 : ${his }<br>
총 점 : ${tot }<br>
평균 : ${avg }<br>
등급 : ${grade }<br>


</body>
</html>
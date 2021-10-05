<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/inc_header.jsp"%>

<a href="${path }">HOME</a>
|
<a href="${path }/test_servlet/test01.do">test01</a>
|
<a href="${path }/test_servlet/test02.do">test02</a>
|
<a href="${path }/test_servlet/test03.do">test03</a>
|
<a href="${path }/test_servlet/test04.do">test04</a>
|
<a href="${path }/test_servlet/test05.do">test05</a>
|
<a href="${path }/test_servlet/test06.do">test06</a>
|
<a href="${path }/test_servlet/test07.do">test07</a>
|
<a href="${path }/test_servlet/test08.do">test08</a>
|
<a href="${path }/test_servlet/test09.do">test09</a>
|
<a href="${path }/test_servlet/test10.do">test10</a>
|
<a href="${path }/test_servlet/test11.do">test11</a>

<br>

<a href="${path }/member_servlet/memberList.do">회원관리</a>
|
<a href="${path }/sungjuk_servlet/sungjukList.do">성적관리</a>
|
<a href="${path }/memo_servlet/memoList.do">메모관리</a>
|
<a href="${path }/guestbook_servlet/guestbookList.do">방명록</a>
|
<a href="${path }/product_servlet/productList.do">상품관리</a>

&nbsp;&nbsp;&nbsp;
<c:choose>
	<c:when test="${sessionScope.cookNor == null || sessionScope.cookNor == 0}">
		<a href="${path }/member_servlet/memberLogin.do">로그인</a>
	</c:when>
	<c:otherwise>
		[<b style="color: blue">${sessionScope.cookName }</b>]님,
		<a href="${path }/member_servlet/memberLogout.do">로그아웃</a>	
	</c:otherwise>
</c:choose>



	

<hr>
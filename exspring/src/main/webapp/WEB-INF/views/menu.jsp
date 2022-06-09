<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
<%-- ${sessionScope.loginUser==null }  sessionScope 생략가능
세션에 저장된 값 꺼내올때 sessionScope을 사용. 세션에 loginUser이름으로 저장된 속성을 가져와라! 로그인 안했으면 null이 나옴. --%>
	<%-- 로그인 안했을 경우 --%>
	<c:if test="${loginUser==null }">
		<a href="${pageContext.request.contextPath}/member/login.do">로그인</a> | 
		<a href="${pageContext.request.contextPath}/member/add.do">회원가입</a>
	</c:if>
	
	<%-- 로그인 했을 경우 --%>
	<c:if test="${loginUser!=null }">
		<c:out value="${loginUser.memName}"></c:out> 님 | 	<%-- 악성스크립트 대시해서 c:out에 출력(보안) --%>
		<a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a> 
	</c:if>
	<hr /> 
</div>	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <%-- <p> x : ${mv.x} </p> --%>
 <%-- <p> y : ${mv.y} </p> --%>
 <p> 계산결과 : ${mv.x} + ${mv.y} = ${mv.sum} </p>
 <p> 현재서버 시간 : <fmt:formatDate value="${time}" pattern="yyyy년 M월 d일 HH시 mm분"/> </p>
 <div>테스트: ${date}</div>
</body>
</html>
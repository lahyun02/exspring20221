<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>Model에 저장한 값 : ${modelVal}</P>
<P>ModelMap에 저장한 값 : ${modelMapVal}</P>
<P>Map에 저장한 값 : ${mapVal}</P>

<P>@ModelAttribute로 저장한 값 : ${mv.myId}, ${mv.myName}</P>
<P>@ModelAttribute를 생략한 값 : ${myVo.myId}, ${myVo.myName}</P>
<%-- @ModelAttribute를 생략해서 앞글자 소문자로 자동변경된 이름으로 사용 가능. --%>


</body>
</html>

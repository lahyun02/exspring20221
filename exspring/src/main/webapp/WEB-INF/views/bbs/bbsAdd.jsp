<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
</head>
<body>

<h1>게시글 추가</h1>


<form action='${pageContext.request.contextPath}/bbs/add.do' method='post'>
글제목 : <input type='text' name='bbsTitle'/><br>
글내용 : <textarea rows="10" cols="50" name="bbsContent"></textarea> <br>
작성자 : <input type='text' name='bbsWriter'/><br>
<input type='submit' />
</form>

</body>
</html>

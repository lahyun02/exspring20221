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
	<table>
		<tr>
			<th scope="row">제목</th>
			<td><input type='text' name='bbsTitle'/></td>
		</tr>
		<tr>
			<th scope="row">내용</th>
			<td><textarea rows="10" cols="50" name="bbsContent"></textarea></td>
		</tr>
		<tr>
			<th scope="row">작성자</th>
			<td><input type='text' name='bbsWriter'/></td>
		</tr>
	</table>
	<input type='submit' />

<%-- 글제목 : <input type='text' name='bbsTitle'/><br>
글내용 : <textarea rows="10" cols="50" name="bbsContent"></textarea> <br>
작성자 : <input type='text' name='bbsWriter'/><br>
<input type='submit' /> --%>

</form>

</body>
</html>

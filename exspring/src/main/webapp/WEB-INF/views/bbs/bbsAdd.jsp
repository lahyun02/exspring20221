<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
<style>
	a {
		text-decoration: none;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/comm/menu.jsp" />

<h1>게시글 추가</h1>


<form action='${pageContext.request.contextPath}/bbs/add.do' method='post'>
	<table class="table table-bordered">
		<tr>
			<th scope="row">제목</th>
			<td><input type='text' name='bbsTitle'/></td>
			<%-- BbsVo의 속성명(변수명)과 파라미터 이름(name속성)이 같아야 한다. --%>
		</tr>
		<tr>
			<th scope="row">내용</th>
			<td><textarea rows="10" cols="50" name="bbsContent"></textarea></td>
		</tr>
		<%-- <tr>
			<th scope="row">작성자</th>
			<td><input type='text' name='bbsWriter' value="${sessionScope.loginUser.memId }" /></td>
			이렇게 하면 js등으로 보안이 뚫릴수있음. 자바에서 처리해야.
		</tr> --%>
	</table>
	<input class="btn btn-light" type='submit' value="저장" /> 

<%-- 글제목 : <input type='text' name='bbsTitle'/><br>
글내용 : <textarea rows="10" cols="50" name="bbsContent"></textarea> <br>
작성자 : <input type='text' name='bbsWriter'/><br>
<input type='submit' /> --%>

</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</body>
</html>

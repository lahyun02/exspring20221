<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
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

<h1>게시글변경</h1>

<form action='${pageContext.request.contextPath}/bbs/edit.do' method='post'>
	<table class="table table-bordered">
		<tr>
			<th scope="row">제목</th>
			<td><input type='text' name='bbsTitle' value="${bbsVo.bbsTitle}" /></td>
		</tr>
		<tr>
			<th scope="row">내용</th>
			<td><textarea rows="10" cols="50" name="bbsContent"><c:out value="${bbsVo.bbsContent}"></c:out></textarea></td>
		</tr>
		<tr>
			<th scope="row">작성자</th>
			<td><c:out value="${bbsVo.bbsWriter}" /></td>
			<%-- 문자열은 보안측면에서 c:out 태그를 써준다. --%>
		</tr>
		<tr>
			<th scope="row">작성일</th>
			<td><fmt:formatDate value="${bbsVo.bbsRegDate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/></td>
		</tr>
	</table>
	<br/>
		<input type="hidden" name="bbsNo" value="${bbsVo.bbsNo}" />
		<input class="btn btn-light" type='submit' value="수정" /> 
<button class="btn btn-light" type="button"><a href='${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}'>삭제</a></button>
<button class="btn btn-light" type="button"><a href='${pageContext.request.contextPath}/bbs/list.do'>목록</a></button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</body>
</html>

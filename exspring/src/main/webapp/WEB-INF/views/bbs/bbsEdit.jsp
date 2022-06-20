<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
<style>
a {
	text-decoration : none;
}
</style>
</head>
<body>

<h1>게시글변경</h1>

<form action='${pageContext.request.contextPath}/bbs/edit.do' method='post'>
	<table>
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
			<td><input type='text' name='bbsWriter' value="${bbsVo.bbsWriter}" readonly/></td>
		</tr>
	</table>
		<input type="hidden" name="bbsNo" value="${bbsVo.bbsNo}" />
		<input type='submit' value="수정" />
		<button><a href='${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}'>삭제</a></button>
		<button><a href='${pageContext.request.contextPath}/bbs/list.do'>목록</a></button>
	

		<%-- 제목 : <input type='text' name='bbsTitle' value="${bbsVo.bbsTitle}" /><br>
		내용 : <textarea rows="10" cols="50" name="bbsContent"><c:out value="${bbsVo.bbsContent}"></c:out></textarea><br>
		작성자 : <input type='text' name='bbsWriter' value="${bbsVo.bbsWriter}" readonly/> <br>
		<input type="hidden" name="bbsNo" value="${bbsVo.bbsNo}" />
		<input type='submit' value="수정" />
		<a href='${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}'>삭제</a>
		<a href='${pageContext.request.contextPath}/bbs/list.do'>목록</a> --%>
	
</form>
		

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>bbs게시판</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

<style>
	a {
		text-decoration: none;
	}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/comm/menu.jsp" />
	<h1>게시판목록</h1>
	<a href='${pageContext.request.contextPath}/bbs/add.do'><button class="btn btn-light" type="button">게시글추가</button></a><br/>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="vo" items="${bbsList}">
				<tr>
					<td><c:out value="${vo.bbsNo}" /></td>
					<td><a href="${pageContext.request.contextPath}/bbs/edit.do?bbsNo=${vo.bbsNo}"><c:out value="${vo.bbsTitle}" /></a></td>
					<%--@GetMapping("edit.do")의 파라미터 변수 명 (int bbsNo)와 윗줄에서 파라미터이름 bbsNo와 이름이 일치해야 한다. 
					다르게 하고 싶으면 @RequestParam을 이용해서 변수명 다르게 할 수 있음.
					파라미터값을 전달하기 위해서 파라미터 이름이 일치해야하기 때문. --%>
					<td><c:out value="${vo.bbsWriter}" /></td>
					<td><fmt:formatDate value="${vo.bbsRegDate}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
		<!DOCTYPE html>
		<html>
		<head>
		<meta charset='UTF-8'>
		<title>Insert title here</title>
		<style>
		a {text-decoration : none}
		</style>
		</head>
		<body>
		<%-- 다른 jsp파일 경로를 가져오는 방법 3가지. 최상단에 가져와 저장. --%>
		<%-- 다른 jsp파일을 가져와서 삽입해서 하나의 서블릿으로 만들어 출력 소스를 포함시킴  --%>
		<%--<%@ include file="" %>--%>
		
		<%-- 다른 jsp파일로 점프해서 그 jsp파일을 실행시킨 다음 돌아와서 이 파일 실행. 이 방법으로 한다.  --%>
		<jsp:include page="/WEB-INF/views/menu.jsp" />
		
		<%-- 현재 프로젝트 외에 다른 사이트 구글, 네이버 의 결과도 가져와서 사용가능   --%>
		<%--<c:import url=""></c:import>--%>
		
		
		
		<h1>회원목록</h1>
		<a href='${pageContext.request.contextPath}/member/add.do'>회원추가</a><br>
		<a href='<c:url value="/member/add.do" />'>회원추가</a><br>
		회원아이디 : 회원이름 : 회원포인트<br>
<%-- 문자열을 출력하는 경우 보안 때문에 c:out(<를 html entity- &lt; 등으로 변환)의 value에 el값을 넣어주는 게 좋다. 
입력창에 자바스크립트 코드를 작성하면 그대로 실행되어 보안 등에 위협적(악성 스크립트 위험 등) 
int타입 memPoint는 int타입에 문자열인 스크립트소스를 넣을 수 없으니 굳이 c:out에 안넣어도 됨. 
실무에선 뭐가 String, int타입인지 헷갈리기 때문에 c:out에 넣으라고 하는 경우 있음.
c:out은 excapeXml 속성이 디폴트로 설정되어서 <(꺽쇠)를 html entity(&lt;)로 자동 변환해줌. --%>
<c:forEach var="vo" items="${memList}">
		<%-- 내가 상세조회하고 싶은 회원의 id를 파라미터를 붙여서 전송해야 해당 회원의 정보가 나온다. --%>
 		<a href="${pageContext.request.contextPath}/member/edit.do?memId=${vo.memId}"><c:out value=" ${vo.memId}" /></a> 
 		: <c:out value="${vo.memName}"  /> : <c:out value="${vo.memPoint}" />
		<a href='${pageContext.request.contextPath}/member/del.do?memId=${vo.memId}'>삭제</a><br>
</c:forEach>
<%-- a태그의 href='' 속성에 들어가는 경우, script태그가 해석되지 않아서 c:out에 el을 넣어서 값을 넣어주지 않아도 됨. --%>

		</body>
		</html>
		
    

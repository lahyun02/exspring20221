<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>${myVo.myId}</p> <%-- el에서는 getMyId()메소드 굳이 호출 안하고 myId라는 속성이름을 써도 된다. --%>
	<p>${myVo.myName}</p>
</body>
</html>
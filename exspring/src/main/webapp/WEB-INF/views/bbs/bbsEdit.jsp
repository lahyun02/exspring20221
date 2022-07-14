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
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.6.0.js"></script>
<!-- webapp이 최상위 폴더니까 webapp까지의 경로를 삭제한 다음 ${pageContext.request.contextPath}를 붙여준다. -->
 
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
		<input type="hidden" name="bbsNo" value="${bbsVo.bbsNo}" />
		<input type='submit' class="btn btn-light" value="수정" /> 
<button class="btn btn-light" type="button"><a href='${pageContext.request.contextPath}/bbs/del.do?bbsNo=${bbsVo.bbsNo}'>삭제</a></button>
<button class="btn btn-light" type="button"><a href='${pageContext.request.contextPath}/bbs/list.do'>목록</a></button>
</form>

<hr/>
<%-- 댓글의 내용이 길어질 수 있으니 get보단 post 방식으로 보낸다. --%>
<%-- form태그 action="${pageContext.request.contextPath}/reply/add.do" method="post" 안쓰니까 생략 가능  --%>
<form id="replyForm"> 
	<%-- 부모글번호를 전송. 어느 글번호에 대한 댓글인지 알수있어야하니까 데이터를 보낸다.  --%>
	<input type="hidden" name="repBbsNo" value="${bbsVo.bbsNo}" /> 
	<textarea name="repContent" rows="5" cols="50"></textarea> 
	<%-- Vo의 변수명과 파라미터 이름을 같게함. Vo변수명으로 데이터(textarea의 내용값)를 받을 거니까. --%>
	<input type="button" value="저장" id="saveBtn" /> 
	<%-- ajax를 사용하니까 type을 submit이 아닌 button으로 고친다.  --%>
</form>

<hr />
<div id="replyDiv">

</div>

<script>
	//똑같은 걸 다시 쓰기 위해 함수로 만든다. 
	function refreshReplyList() {
		$.ajax({  //요청보내기
		  url: "${pageContext.request.contextPath}/reply/list.do",  //요청주소
		  method: "GET",	//요청방식
		  data: { 
			  repBbsNo : $('[name="repBbsNo"]').val()
			},  //파라미터
		  dataType: "json"  //text->문자열로 받겠다	//요청의 결과(서버의 응답)으로 받을 데이터의 형식  
		}).done(function( msg ) {  //요청에 대한 응답을 성공적으로 받았을때 실행할 함수 //응답받기
			  console.log( msg ); // List<ReplyVo>
			  $('#replyDiv').empty();
			  for(var i = 0; i < msg.length; i++) {
				  var vo = msg[i];
				  console.log(vo.repNo, vo.repContent, vo.repWriter, vo.repRegDate);
				  //$('<div>') //화면에 없는 div 태그를 새로 만드는 것. $('div') //화면에 있는 div 엘리먼트를 찾아오는 것.
	//			  $('#replyDiv').append( '<div>' + vo.repContent + '</div>' ); 
	//			  $('#replyDiv').append( $('<div>').text(vo.repContent) ); 
				  $('<div>').text( vo.repWriter ).appendTo( '#replyDiv' );    //<div>vo.repWriter</div>
				  $('<div>').text( vo.repContent ).appendTo( '#replyDiv' );    //<div>vo.repContent</div>
				  $('<div>').text( vo.repRegDate ).appendTo( '#replyDiv' );    //<div>vo.repRegDate</div>
				  $('<button>').text('삭제').appendTo( '#replyDiv' );    //<button>삭제</button>
				  $('<hr>').appendTo('#replyDiv');    //<hr></hr>
			  }
		}).fail(function( jqXHR, textStatus ) {  //요청이 실패한 경우 실행할 함수 
			alert( "Request failed: " + textStatus );
		});
	}

	refreshReplyList();

	$('#saveBtn').on('click', function() {
		$.ajax({  //요청보내기
			  url: "${pageContext.request.contextPath}/reply/add.do",  //요청주소
			  method: "POST",	//요청방식
//			  data: 'repBbsNo='+$('[name="repBbsNo"]').val()+''+ &repContent='+$('[name="repContent"]').val()',//불편, 파라미터에 특수문자가 들어갈 경우 인코딩해야함.
			  data: { repBbsNo : $('[name="repBbsNo"]').val(), repContent : $('[name="repContent"]').val() },  //파라미터
//			  data: $('#replyForm').serialize(), //폼 내부의 입력요소들을 파라미터 문자열로 생성 (여러개를 한꺼번에 사용 가능!)
			  dataType: "json"  //text->문자열로 받겠다	//요청의 결과(서버의 응답)으로 받을 데이터의 형식  
			  // 예전 방식 (최신 버전을 권장)
//			  , success: function(msg) {} //성공시 실행할 함수
//			  , error: function( jqXHR, textStatus ) {}  //실패시  실행할 함수
//			  , complete: function() {}  //성공하든 실패하든 항상 실행할 함수 
			}).done(function( msg ) {  //요청에 대한 응답을 성공적으로 받았을때 실행할 함수 //응답받기
				  //서버로부터 받은 응답이 인자로 전달된다 
				  //alert( msg ); //  '{ "no" : 1 }'
//				  var data = JSON.parse(msg);  // '{ "no" : 1}' -> {"no" : 1} 
//				  alert(data.no); //1
				  alert( msg.no + '개의 댓글을 저장했습니다.' ); //1
				  $('[name="repContent"]').val(''); //댓글내용 초기화. 빈 문자열을 값으로 설정
				  refreshReplyList();  //입력한 댓글내용 출력 
				  
			}).fail(function( jqXHR, textStatus ) {  //요청이 실패한 경우 실행할 함수 
				alert( "Request failed: " + textStatus );
			}).always(function() {  //성공하든 실패하든 요청 처리가 끝나면 항상 실행할 함수 
				console.log( "complete " );
			});
			//dataType: "json"이기때문에 제이쿼리가  msg에 문자열을 넣어줄 떄 JSON.parse(msg)를 자동으로 해서 넣어줌. 

			
			
	});

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</body>
</html>

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
					
				  //댓글의 작성자와 로그인한 사람의 아이디가 같으면 삭제 버튼이 보여라.
				  if(vo.repWriter === '${loginUser.memId}' ) {  
					  //${sessionScope.loginUser.memId}에서 sessionScope는 생략 가능. 
					  //vo.repWriter === ${loginUser.memId}에서 ${loginUser.memId}은 변수명으로 해석됨. 문자열로 인식되어야하므로 ''따옴표 작성. 
					  $('<button>').attr('data-no', vo.repNo).addClass('delBtn').text('삭제').appendTo( '#replyDiv' );    //<button>삭제</button>
				  }
				  //.attr('속성이름', '속성값') 커스텀 속성 지정가능 기존 html태그나 커스텀속성 가능(원래있던 태그와 충돌/추후 버전업에서 추가될 가능성)
				  $('<hr>').appendTo('#replyDiv');    //<hr></hr>
			  }
		}).fail(function( jqXHR, textStatus ) {  //요청이 실패한 경우 실행할 함수 
			alert( "Request failed: " + textStatus );
		});
	}
	
	//현재 문서에 존재하지 않는, 동적으로 바뀌게 하고 싶을때 이런 방식 사용
	//정적으로 존재하는애       //동적으로 변하게하고 싶은 애
	$('#replyDiv').on('click', '.delBtn', function() {
		alert('삭제!');
		
		$.ajax({  //요청보내기
			  url: "${pageContext.request.contextPath}/reply/del.do",  //요청주소
			  method: "GET",	//요청방식
			  data: { 
				  repNo : $(this).attr('data-no')
				},  //파라미터 //클릭이벤트가 일어난 대상(this)의 data-no속성값
				//this는 제이쿼리 객체가 아님. $(this) -> 제이쿼리 객체를 만듦. attr()은 제이쿼리 객체.
				//값을 안주면 getter로 동작, 값을 주면 setter로 동작-> 대부분 api가 그렇게 설정되어 있음. 
			  dataType: "json"  //text->문자열로 받겠다	//요청의 결과(서버의 응답)으로 받을 데이터의 형식  
			}).done(function( msg ) {  //요청에 대한 응답을 성공적으로 받았을때 실행할 함수 //응답받기
				  console.log( msg ); // { no : 1 }
				  alert(msg.no + '개의 댓글이 삭제되었습니다.')
				  refreshReplyList(); //댓글목록 출력 
			}).fail(function( jqXHR, textStatus ) {  //요청이 실패한 경우 실행할 함수 
				alert( "Request failed: " + textStatus );
			});
	}); 
	
	//document에 클릭이 발생했는데, .delBtn 이 선택자에게클릭 이벤트가 발생하면 해당 함수 실행
	//조상에게 함수를 걸어줌. 클릭이벤트가 .delBtn에 걸려있는지 검사한 후 맞으면 함수 실행
	
	//$('.delBtn')~ -> 해당 시점에선 refreshReplyList();실행 전이므로 삭제 버튼이 없었음.
	// refreshReplyList(); 밑으로 순서를 바꿔주면 당장은 해결되어보일 지 몰라도 
	//$('#saveBtn')~ 함수가 실행돼서 refreshReplyList();로 초기화되면 삭제버튼이 없어지고 새로 생기기 때문에 안됨. 
	
	//문서가 랜더링된 후 클릭이벤트가 발생할 때마다 계속 '.delBtn'을 검사함. 성능에 비효율적
	//$('#replyDiv') -> 삭제버튼은 #replyDiv영역 안에 있으므로.
	
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

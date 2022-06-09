package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//과제 : (1)번부터 구현 
//(1)변경하려는 회원아이디가 로그인한 회원의 아이디와 다르다면 변경되지 않도록 구현
//-> 보안은 자바에서 한다! 화면에서 절대 막을 수 없음.
//(2)회원정보변경 화면에서도, 화면의 회원아이디가 로그인한 회원의 아이디와 다르다면,
//   이름, 포인트 값 변경이 불가능하고 submit 버튼도 출력되지 않도록 구현

//폼 작성으로 회원추가를 할 수 있는 서블릿
@WebServlet("/member/edit.do")
public class MemEditServlet extends HttpServlet {
	MemberDao memberDao = MemberDaoBatis.getInstance();	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//화면에 출력하기 전에 데이터베이스에서 해당회원의 정보를 꺼내와야함.
		String memId = req.getParameter("memId");
		
		// 회원정보를 꺼내올 메소드 (MemberDao에서)
		MemberVo vo = memberDao.selectMember(memId);
		
		req.setAttribute("memVo", vo);
		
		HttpSession session = req.getSession(); 
		MemberVo userVo = (MemberVo) session.getAttribute("loginUser");
		req.setAttribute("userId", userVo.getMemId()); 
		
		req.getRequestDispatcher("/WEB-INF/jsp/member/MemEdit.jsp").forward(req, resp);	
		//jsp에 출력할 정보가 없기 때문에 add에선 이것만 했었음.
		// 여기에선 db에서 꺼내온 정보-vo-를 출력해야함. -> 요청객체에 속성으로 작성에서 jsp에서 요청객체에 저장된 걸 꺼내씀.
		
		// doGet메소드에서 아무리 로그인이외사람 접근 막아도 못막는다.
		// -> 화면에서 변경해도 db에선 변경이 안되기 때문! 위에 dao메소드가 select로 회원조회하는 메소드다...
		// doPost에서 로그인이외사람 접근을 막았기 때문에 doGet에선 안막아줘도 됨. 소용없기 때문.
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 검사
		HttpSession session = req.getSession();
		MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
		if(!mvo.getMemId().equals(req.getParameter("memId"))) {		
			//로그인회원아이디와 변경할회원아이디가 다르면 
			//오류페이지를 보여주거나 특정페이지로 리다이렉트시킬 수 있음.
			throw new RuntimeException("로그인한 사용자와 다른 회원 정보는 변경 불가");
			// 일부로 오류처리 했기 때문에 return은 실행시킬 필요 없으므로 return에 오류가 남.
//			return; 	// 아래쪽은 실행 안되므로 막힘. -다른 페이지로 리다이렉트 등 의 경우 필요
		}
		
		//db에 추가 
//		req.setCharacterEncoding("UTF-8"); // 웹애플리케이션을 post방식으로 할때 항상 해줘야 하는 작업 -> 필터에서 해줌.
		MemberVo vo = new MemberVo();
		vo.setMemId( req.getParameter("memId") ); 
//		vo.setMemPass( req.getParameter("memPass") ); 
		vo.setMemName( req.getParameter("memName") ); 
		vo.setMemPoint( Integer.parseInt( req.getParameter("memPoint") ) ); 
		int num = memberDao.updateMember(vo); 	// db에 업데이트 하는 부분. 이부분을 막아야 한다. 이부분 전에 로그인된 사람 이외에 변경 못하도록 막아야 한다.
		
		//resp.sendRedirect("이동할사이트주소"); 명령을 사용하여, 웹브라우저에게 특정 사이트로 이동하라는 명령을 담은 응답을 전송.
		// 서버 주소가 다를 수 있으니까 주소 앞에 http://localhost:8000는 생략 
		resp.sendRedirect( req.getContextPath() + "/member/list.do");
		
		//결과 출력 
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<meta charset='UTF-8'>");
//		out.println("<title>Insert title here</title>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("<h1>회원추가</h1>");
//		out.println( num + "명의 회원이 추가되었습니다.");
//		out.println("</body>");
//		out.println("</html>");
		
	}
}


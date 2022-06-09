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


//폼 작성으로 회원추가를 할 수 있는 서블릿
@WebServlet("/member/add.do")
public class MemAddServlet extends HttpServlet {
	MemberDao memberDao = MemberDaoBatis.getInstance();
	
	//서블릿의 service() 메서드 : 요청방식에 상관없이 실행되는 메서드
	//서블릿의 doXxx() 메서드 : 요청방식이 XXX인 경우에 실행되는 메서드
	// 상속받은 HttpServlet 클래스의 메소드에 이미 다 구현되어 있어서 이런 방식이 가능함. 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/MemAddForm.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//db에 추가 
//		req.setCharacterEncoding("UTF-8"); // 웹애플리케이션을 post방식으로 할때 항상 해줘야 하는 작업 -> 필터에서 해줌.
		MemberVo vo = new MemberVo();
		vo.setMemId( req.getParameter("memId") ); 
		vo.setMemPass( req.getParameter("memPass") ); 
		vo.setMemName( req.getParameter("memName") ); 
		vo.setMemPoint( Integer.parseInt( req.getParameter("memPoint") ) ); 
		int num = memberDao.insertMember(vo);
		
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


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


//폼 작성으로 회원추가를 할 수 있는 서블릿
@WebServlet("/member/login.do")
public class LoginServlet extends HttpServlet {
	MemberDao memberDao = MemberDaoBatis.getInstance();	
	
	//서블릿의 service() 메서드 : 요청방식에 상관없이 실행되는 메서드
	//서블릿의 doXxx() 메서드 : 요청방식이 XXX인 경우에 실행되는 메서드
	// 상속받은 HttpServlet 클래스의 메소드에 이미 다 구현되어 있어서 이런 방식이 가능함. 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/login.jsp").forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//db에 추가 
//		req.setCharacterEncoding("UTF-8"); // 웹애플리케이션을 post방식으로 할때 항상 해줘야 하는 작업 -> 필터에서 해줌.
		MemberVo vo = new MemberVo();
		vo.setMemId( req.getParameter("memId") ); 
		vo.setMemPass( req.getParameter("memPass") ); 
		
		MemberVo memVo = memberDao.selectLoginMember(vo);
		// 회원정보와 다른 걸 입력하면(잘못입력)-> memVo에는 null값이 온다. 
		
		if(memVo==null) { 	//로그인이 실패한 경우, 
			resp.sendRedirect( req.getContextPath() + "/member/login.do");	//다시 로그인 페이지로 이동 
		}else { 	//로그인이 성공한 경우
			// 로그인 정보를 세션에 저장 
			HttpSession session = req.getSession(); 	//현재 요청(을보낸사용자)가 속한 세션객체 가져오기
			session.setAttribute("loginUser", memVo); 	//로그인성공한 사용자 정보를 세션에 "loginUser"라는 이름으로 저장 
			resp.sendRedirect( req.getContextPath() + "/member/list.do");	//회원목록 페이지로 이동 
		}
	}
}


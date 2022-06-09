package com.exam.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout.do")
public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		//세션의 속성값을 삭제하는 방법(세션에 있는 로그인 정보 지우기)
		//1. 속성값으로 null을 저장. (login서블릿에서 loginUser이름으로 저장했던 정보를 null로 저장) 
//		session.setAttribute("loginUser", null);
		//2. 속성(자체)를 삭제.
		// 특정 값을 삭제 
//		session.removeAttribute("loginUser");
		//3. 세션객체 전체를 초기화(삭제 후 재생성)
		// 이 세션을 유효하지 않다고 하면 세션이 이 유효하지 않은 세션객체를 지움. -> 사용자가 다시 페이지 이용할 시 새로운 세션이 생김.
		// 세션을 여러개 저장했는데 싹 다 없애버리고 싶다.
		session.invalidate();
		
		resp.sendRedirect( req.getContextPath() + "/member/login.do");	//다시 로그인 페이지로 이동 
		
		
		
		
	}
	
}

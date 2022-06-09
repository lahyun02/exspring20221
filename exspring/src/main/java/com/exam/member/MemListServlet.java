package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//과제
//1. 학생목록, 추가, 삭제 서블릿과 JSP 파일을 구현(MVC패턴)
//2. 학생정보 수정 기능 추가

//회원목록을 출력해주는 서블릿
@WebServlet("/member/list.do")
public class MemListServlet extends HttpServlet {
	MemberDao memberDao = MemberDaoBatis.getInstance();	//한번만 실행해도 됨.
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//if문 실행후 밑에가 실행이 안되도록 하려면- if문 안에 return을 넣거나 else안에 밑의 실행문을 넣거나.
		List<MemberVo> list = memberDao.selectMemberList(); //요청이 올때마다 실행해야 함. 테이블의 내용이 변경될 수 있기 때문.
		req.setAttribute("memList", list);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/member/MemList.jsp");
		rd.forward(req, resp);
		
//		resp.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head>");
//		out.println("<meta charset='UTF-8'>");
//		out.println("<title>Insert title here</title>");
//		out.println("<style>");
//		out.println("a {text-decoration : none}");
//		out.println("</style>");
//		out.println("</head>");
//		out.println("<body>");
//		out.println("<h1>회원목록</h1>");
//		out.println("<button><a href='" + req.getContextPath() + "/member/addform.do'>회원추가</a></button><br>");
//		out.println("회원아이디 : 회원비밀번호 : 회원이름 : 회원포인트<br>");
//		
//		for(int i=0; i < list.size(); i++) {
//			MemberVo vo = list.get(i);
//			out.println(vo.toString());
//			out.println("&nbsp;&nbsp;<button><a href='" + req.getContextPath() + "/member/del.do?memId=" + vo.getMemId() + "'>삭제</a></button><br>");
//		}
//		// /슬래쉬부터 시작하는 경로니까 고유경로인 exweb을 앞에 써줌. 직접 써주는 것보단 변수로 받는 게 더 좋음. 
//		///member/del.do?memId=삭제할 회원 아이디
//		
//		out.println("</body>");
//		out.println("</html>");
		
	}
}


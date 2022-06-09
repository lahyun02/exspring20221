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
@WebServlet("/member/del.do")
public class MemDelServlet extends HttpServlet {
	MemberDao memberDao = MemberDaoBatis.getInstance();	
	
	// 링크눌렀을 때 실행. 링크 누르면 get방식. 
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getParameter("abc").equals("def"); // nullpointexception 테스트하기 위해 일부로 오류내기.		
		
		req.setCharacterEncoding("UTF-8");
		String memId = req.getParameter("memId"); 
		int num = memberDao.delMember( memId );
		
		resp.sendRedirect( req.getContextPath() + "/member/list.do");
		
		//출력
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
//		out.println("<h1>회원삭제</h1>");
//		out.println( num + "명의 회원이 삭제되었습니다.");
//		out.println("</body>");
//		out.println("</html>");
		
	}
}


package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//폼 작성으로 회원추가를 할 수 있는 서블릿
//@WebServlet("/member/addform.do")
public class MemAddFormServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("/WEB-INF/jsp/member/MemAddForm.jsp").forward(req, resp);
		
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
//		
//		// /exweb -> 고유경로를 명령어를 통해 사용. 나중에 경로를 바꿔도 사용 가능하도록.
//		out.println("<form action='" + req.getContextPath() + "/member/add.do' method='post'>");
//		out.println("아이디 : <input type='text' name='memId'/><br>");
//		out.println("비밀번호 : <input type='password' name='memPass'/><br>");
//		out.println("이름 : <input type='text' name='memName'/><br>");
//		out.println("포인트 : <input type='text' name='memPoint'/><br>");
//		out.println("<input type='submit' />");
//		out.println("</form>");
//		
//		out.println("</body>");
//		out.println("</html>");
		
	}
}


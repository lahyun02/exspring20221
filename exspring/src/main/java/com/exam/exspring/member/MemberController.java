package com.exam.exspring.member;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//서블릿은 스프링에서 만든 dispatcher-servlet이 있어서 HttpServlet 상속은 생략
//@Controller붙이기
//@WebServlet 대신 @RequestMapping 써서 요청주소 uri
// 메소드를 오버라이드 할 것도 없어서 오버라이드 생략
//throws ServletException, IOException 서블릿의 약속된 형식이었기 때문에 써줬지만 지금은 서블릿을 상속받지도 않으니 굳이 이렇게 예외덛질 필요x

// 스프링이 MemberController 객체를 만들어준다는 근거: 
//servlet-context에서 <context:component-scan base-package="com.exam.exspring" /> -> 경로에 맞춰 설정해준다. 

//HttpServletRequest req, HttpServletResponse resp -> POJO원칙에 따라 사용지양

//회원목록
@Controller
public class MemberController { 
	//스프링에서 memberDao변수에 넣을 수 있는 MemberDao 타입의 애들 자동 주입
	@Autowired
	private MemberService memberService;	// 싱글톤 패턴 -스프링은 기본이 싱글톤이어서 스프링이 자동으로 싱글톤으로 관리해줌.
	//MemberDaoBatis.getInstance() 메소드는 MemberDaoBatis에서 삭제했기 때문에 오류 -> 삭제
	//접근 제한자 -> 붙여도 되고 안붙여도 되지만 어차피 외부에서 쓰지 않기 때문에  
	
	@RequestMapping(value = "/member/list.do", method = RequestMethod.GET)
	public String list(Map<String,Object> map)  {	// 요청객체와 응답객체는 스프링이 알아서 해줌 -> 직접 하는 대신 모델에 넣어준다. 
		List<MemberVo> list = memberService.selectMemberList();  //데이터베이스에서 회원목록 꺼내오기
		map.put("memList", list);
		return "member/memList";
	}
	
	// 메소드이름이 겹치면 안되기 때문에 변경
	@RequestMapping(value = "/member/add.do", method = RequestMethod.GET)
	public String addForm() {
		return "member/memAdd";	
	}
	
	@RequestMapping(value = "/member/add.do", method = RequestMethod.POST)
	public String add(MemberVo vo) {
		// 파라미터이름과 객체속성이름이 동일하면 (파라미터이름) 생략 가능
		
		int num = memberService.insertMember(vo);
		return "redirect:/member/list.do";
		// 뷰 이름에 redirect: 접두어를 사용하여 (포워드가 아닌) 리다이렉트임을 표시
		//포워딩(return "/member/list.do" -> /WEB-INF/views//member/list.do.jsp 되기 때문)아니라 redirect(요청주소)라는 걸 알려준다.
	}
	
	@RequestMapping(value = "/member/edit.do", method = RequestMethod.GET)
	public String editForm(String memId, Map<String,Object> map) {
		MemberVo vo = memberService.selectMember(memId);  // 회원정보를 꺼내올 메소드 
		map.put("memVo", vo);
		return "member/memEdit";	
	}
	
	@RequestMapping(value = "/member/edit.do", method = RequestMethod.POST)
	public String edit(MemberVo vo) { 
//		HttpSession session = req.getSession();
//		MemberVo mvo = (MemberVo) session.getAttribute("loginUser");
//		if(!mvo.getMemId().equals(req.getParameter("memId"))) {		
//			throw new RuntimeException("로그인한 사용자와 다른 회원 정보는 변경 불가");
//		}
		
		int num = memberService.updateMember(vo); 	// db에 업데이트 하는 부분. 이부분을 막아야 한다. 이부분 전에 로그인된 사람 이외에 변경 못하도록 막아야 한다.
		return "redirect:/member/list.do"; 
	}
	
	
	@RequestMapping(value = "/member/del.do", method = RequestMethod.GET)
	public String del(String memId) {
		int num = memberService.delMember( memId );
		return "redirect:/member/list.do"; 
	}
	
	
	
	
}


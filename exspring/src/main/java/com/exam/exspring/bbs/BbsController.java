package com.exam.exspring.bbs;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.exam.exspring.member.MemberVo;

//서블릿은 스프링에서 만든 dispatcher-servlet이 있어서 HttpServlet 상속은 생략

// 스프링이 MemberController 객체를 만들어준다는 근거: 
//servlet-context에서 <context:component-scan base-package="com.exam.exspring" /> -> 경로에 맞춰 설정해준다. 

//HttpServletRequest req, HttpServletResponse resp -> POJO원칙에 따라 사용지양

//게시글목록(글번호, 글제목, 작성자, 작성일) /bbs/list.do
//게시글추가(글제목, 글내용, 작성자) /bbs/add.do
//게시글변경(제목,내용) /bbs/edit.do
//게시글삭제(변경화면에삭제버튼클릭시 ) /bbs/del.do
//BbsController.java, BbsService.java, BbsServiceImpl.java, BbsDao.java,
//BbsMapper.xml
//bbsList.jsp, bbsAdd.jsp, bbsEdit.jsp

//회원목록
@Controller
@RequestMapping("/bbs/") //이 컨트롤러 내부의 @RequestMapping 메서드들의 공통경로를 클래스에 설정 가능. 모든 버전에서 사용 가능.
public class BbsController { 
	//스프링에서 memberDao변수에 넣을 수 있는 MemberDao 타입의 애들 자동 주입
	@Autowired
	private BbsService bbsService;	// 싱글톤 패턴 -스프링은 기본이 싱글톤이어서 스프링이 자동으로 싱글톤으로 관리해줌.
	//MemberDaoBatis.getInstance() 메소드는 MemberDaoBatis에서 삭제했기 때문에 오류 -> 삭제
	//접근 제한자 -> 붙여도 되고 안붙여도 되지만 어차피 외부에서 쓰지 않기 때문에  
	
	// 게시글목록(글번호, 글제목, 작성자, 작성일) /bbs/list.do
//	@RequestMapping(value = "list.do", method = RequestMethod.GET)
//	@GetMapping(value = "list.do")    //스프링4.3이상부터 사용가능 
	@GetMapping("list.do")   		   // 어노테이션에서 value값이 하나만 있을땐 생략 가능
	public String list(Map<String, Object> map) {
		List<BbsVo> list = bbsService.selectBbsList();
		map.put("bbsList", list); 
		return "bbs/bbsList";   //  /WEB-INF/views/bbs/bbsList.jsp
	}
	
	// 게시글 추가 폼
//	@RequestMapping(value = "add.do", method = RequestMethod.GET)
	@GetMapping("add.do") 
	public String addForm() {
		return "bbs/bbsAdd";
	}
	
	// 게시글 추가하기
//	@RequestMapping(value = "add.do", method = RequestMethod.POST)
	@PostMapping("add.do")
	public String add(BbsVo vo, HttpSession session, @SessionAttribute("loginUser") MemberVo memVo) {
		//@SessionAttribute -> 4.3이상부터 가능. 파라미터앞에 SessionAttribute 이름으로 저장된 세션 정보를 memVO에 저장해 사용가능.
		//게시판 추가에서(bbsAdd.jsp) 작성한 정보가 파라미터 안의 BbsVo vo로 들어가서 저장된다.
		//세션에 로그인한 사람의 아이디를 loginUser라는 이름으로 저장해놨음-> MemberController의 login메소드 참고
//		MemberVo memVo = (MemberVo) session.getAttribute("loginUser"); //로그인한 사람의 정보를 세션에서 꺼내오기 (직접)
		// 세션에는 온갖 게 다 저장되니까 우리가 꺼내오고 싶은 MemberVo의 정보만 꺼내오고 싶으므로 MemberVo로 강제 형변환함.
 		vo.setBbsWriter( memVo.getMemId() ); //세션에서 꺼내온 로그인한 사용자의 정보를 작성자로 setter저장하기.
		
		int num = bbsService.insertBbs(vo);
		return "redirect:/bbs/list.do";
	}
	
	// 게시글 수정 폼
//	@RequestMapping(value = "edit.do", method = RequestMethod.GET)
	@GetMapping("edit.do")                             
	public String editForm(int bbsNo, Map<String, Object> map) {
		BbsVo vo = bbsService.selectBbs(bbsNo);
		map.put("bbsVo", vo);
		return "bbs/bbsEdit";
	}
	
	//게시글 수정하기
//	@RequestMapping(value = "edit.do", method = RequestMethod.POST)
	@PostMapping("edit.do")
	public String edit(BbsVo vo) {
		int num = bbsService.updateBbs(vo); 
		return "redirect:/bbs/list.do";
	}
	
	//게시글 삭제하기
//	@RequestMapping(value = "del.do", method = RequestMethod.GET)
	@GetMapping("del.do") 
	public String del(int bbsNo ) {
		System.out.println("aaa");
		int num = bbsService.delBbs(bbsNo);
		return "redirect:/bbs/list.do"; 
	}
	
	
	
}


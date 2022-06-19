package com.exam.exspring.bbs;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class BbsController { 
	//스프링에서 memberDao변수에 넣을 수 있는 MemberDao 타입의 애들 자동 주입
	@Autowired
	private BbsService bbsService;	// 싱글톤 패턴 -스프링은 기본이 싱글톤이어서 스프링이 자동으로 싱글톤으로 관리해줌.
	//MemberDaoBatis.getInstance() 메소드는 MemberDaoBatis에서 삭제했기 때문에 오류 -> 삭제
	//접근 제한자 -> 붙여도 되고 안붙여도 되지만 어차피 외부에서 쓰지 않기 때문에  
	
	// 게시글목록(글번호, 글제목, 작성자, 작성일) /bbs/list.do
	@RequestMapping(value = "/bbs/list.do", method = RequestMethod.GET)
	public String list(Map<String, Object> map) {
		List<BbsVo> list = bbsService.selectBbsList();
		map.put("bbsList", list); 
		return "bbs/bbsList"; 
	}
	
	// 게시글 추가 폼
	@RequestMapping(value = "/bbs/add.do", method = RequestMethod.GET)
	public String addForm() {
		return "bbs/bbsAdd";
	}
	
	// 게시글 추가하기
	@RequestMapping(value = "/bbs/add.do", method = RequestMethod.POST)
	public String add(BbsVo vo) {
		int num = bbsService.insertBbs(vo);
		return "redirect:/bbs/list.do";
	}
	
	// 게시글 수정 폼
	@RequestMapping(value = "/bbs/edit.do", method = RequestMethod.GET)
	public String editForm(int bbsNo, Map<String, Object> map) {
		BbsVo vo = bbsService.selectBbs(bbsNo);
		map.put("bbsVo", vo);
		return "bbs/bbsEdit";
	}
	
	//게시글 수정하기
	@RequestMapping(value = "/bbs/edit.do", method = RequestMethod.POST)
	public String edit(BbsVo vo) {
		int num = bbsService.updateBbs(vo); 
		return "redirect:/bbs/list.do";
	}
	
	//게시글 삭제하기
	@RequestMapping(value = "/bbs/del.do", method = RequestMethod.GET)
	public String del(int bbsNo ) {
		System.out.println("aaa");
		int num = bbsService.delBbs(bbsNo);
		return "redirect:/bbs/list.do"; 
	}
	
	
	
}


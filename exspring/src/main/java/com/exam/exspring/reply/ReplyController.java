package com.exam.exspring.reply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.exspring.member.MemberVo;

@Controller
public class ReplyController {
	@Autowired		//스프링으로부터 자동으로 주입받기
	private ReplyService replyService;
	
	@RequestMapping(path = "/reply/add.do", method = RequestMethod.POST)
	@ResponseBody  //이 메서드의 반환값을 그대로 응답으로 전송 (jsp 경로가 아닌, 문자열로 프린트 )
	public String add(ReplyVo vo, HttpSession session) { 
		//파라미터 값을 받기 위해 파라미터 이름과 같은 이름을 변수명으로 한다.(int repBbsNo, String repContent)
		//다만 값 하나하나씩 일일이 받지 않기 위해 해당 속성을 갖고 있는 ReplyVo 객체로 받는다.
		
		// ReplyVo에 없는 rep_writer 값 설정하기
		MemberVo memVo = (MemberVo) session.getAttribute("loginUser"); //로그인한 사람의 정보를 세션에서 꺼내오기 (직접)
 		vo.setRepWriter( memVo.getMemId() ); //세션에서 꺼내온 로그인한 사용자의 정보를 작성자로 setter저장하기.
 		
 		//데이터베이스 댓글 추가(insert)
 		int num = replyService.insertReply(vo);  //0이면 insert안된거고, 1이면 insert가 잘 된것.

 		return num + "개의 댓글 저장 성공";
	}
	
}

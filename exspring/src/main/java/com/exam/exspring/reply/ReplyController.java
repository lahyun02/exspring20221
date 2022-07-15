package com.exam.exspring.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.exspring.member.MemberVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ReplyController {
	@Autowired		//스프링으로부터 자동으로 주입받기
	private ReplyService replyService;
	
	@RequestMapping(path = "/reply/list.do", method = RequestMethod.GET)
	@ResponseBody
	public List<ReplyVo> list(int repBbsNo) { //파라미터명과 동일한 이름의 변수명 설정
		List<ReplyVo> list = replyService.selectReplyList(repBbsNo); 
		return list;
	}
	
	
	@RequestMapping(path = "/reply/add.do", method = RequestMethod.POST)
	@ResponseBody  //이 메서드의 반환값을 그대로 응답으로 전송 (jsp 경로가 아닌, 문자열로 프린트 )
	public Map<String, Object> add(ReplyVo vo, HttpSession session) { 
		//파라미터 값을 받기 위해 파라미터 이름과 같은 이름을 변수명으로 한다.(int repBbsNo, String repContent)
		//다만 값 하나하나씩 일일이 받지 않기 위해 해당 속성을 갖고 있는 ReplyVo 객체로 받는다.
		
		// ReplyVo에 없는 rep_writer 값 설정하기
		MemberVo memVo = (MemberVo) session.getAttribute("loginUser"); //로그인한 사람의 정보를 세션에서 꺼내오기 (직접)
 		vo.setRepWriter( memVo.getMemId() ); //세션에서 꺼내온 로그인한 사용자의 정보를 작성자로 setter저장하기.
 		
 		//데이터베이스 댓글 추가(insert)
 		int num = replyService.insertReply(vo);  //0이면 insert안된거고, 1이면 insert가 잘 된것.
 		
 		Map<String,Object> map = new HashMap<String, Object>();
 		map.put("no", num); //map에 num값을 담기
 		
 		return map; 
 		//스프링이 아래처럼 자동으로 json으로 바꿔서 보내준다.
 		
// 		String jsonStr = "";  
// 		ObjectMapper mapper = new ObjectMapper(); //JAVA객체 <-> JSON 문자열 변환 담당  
// 		try {
//			jsonStr = mapper.writeValueAsString(map); //JAVA객체 <-> JSON 문자열 변환
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}  
// 		
// 		return jsonStr;  // "{ \"no\" : " + num + " }";
	}
	
	@RequestMapping(path = "/reply/del.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> del(ReplyVo vo, HttpSession session) {
		
		MemberVo memVo = (MemberVo) session.getAttribute("loginUser"); //로그인한 사람의 정보를 세션에서 꺼내오기 (직접)
 		vo.setRepWriter( memVo.getMemId() ); //세션에서 꺼내온 로그인한 사용자의 정보를 작성자로 setter저장하기.
		
		int num = replyService.deleteReply(vo); //mybatis-> 두 개 이상의 정보를 하나에 담아서 줘야함. vo 등
		//JSON으로 보낼거라 값하나만 달랑 보내지 말고 객체 만들어  터이터를 담아서 주기
		
		Map<String,Object> map = new HashMap<String, Object>();
 		map.put("no", num); //map에 num값을 담기
 		
 		return map;  
		
	}
	
	
}


//JSON
//자바스크립트 객체 표현을 그대로 사용
//차이점1 : 문자열 표현에 쌍따옴표만 사용 가능
//차이점2 : 객체의 속성이름을 문자열로 표현(쌍따옴표) 
// { "memId" : "a001", "memPoint" : 10 }




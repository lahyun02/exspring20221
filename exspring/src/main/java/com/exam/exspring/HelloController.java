package com.exam.exspring;


import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {
	
	//	http://localhost:8000/exspring/hello.do?x=3&y=4 로 요청을 보내면,
	// 화면에
	// 계산결과 : 3 + 4 = 7
	// 현재서버 시간 : 2022년 6월 9일 16시 01분 (서버시간 -> 자바로 작성. 자바스크립트는 클라이언트(브라우저) 기준 시간으로 나옴)
	//라고 출력되도록 구현
	// 파라미터 값은 MyValue 객체로 받고,
	// 계산결과는 MyValue 객체의 sum 변수에 저장
	// hello.jsp 파일에서 화면 출력 
	
	@RequestMapping(value = "/hello.do", method = RequestMethod.GET)
	public String hello(Locale locale, @ModelAttribute("mv") MyValue mva, ModelMap modelMap) {
		
		int s = mva.getX() + mva.getY();
		mva.setSum(s);
		Date date = new Date();
		modelMap.addAttribute("time", date);
		
		return "hw/hello";
	}
}

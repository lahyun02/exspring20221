package com.exam.exspring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceView;

/**
 * Handles requests for the application home page.
 */
// 웹 요청을 받아서 실행되는 클래스임을 의미 
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//서블릿에서 수행하던 주요 작업
	//-요청 파라미터 값 받기
	//- JSP에서 사용할 데이터를 요청객체에 저장
	//- 화면을 출력할 JSP파일로 이동(forward)
	// 스프링에선 요청객체에 직접 저장해서 가져오는 걸 선호하지 않는다. map, modelmap, model 객체 이용 
	
	//@RequestMapping : 웹 요청을 받아서 실행되는 코드를 담고 있는 메서드
	//  path 또는 value 값으로, 요청 경로(주소) 설정
	//  method 값으로 요청 방식 설정
	// http://localhost:8000/exspring/home.do 로 요청시 처리

	//애노테이션부터 메소드끝까지 서블릿의 역할 1. 파라미터 받기(요청객체.getParameter() )
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)   
	public String home(Locale locale
			, @RequestParam("myId") int id  //"myId"라는 요청파라미터값을 id 변수에 저장 (자동형변환)
			, String myId  //변수이름과 요청파라미터 이름이 동일하면 @RequestParam 생략 가능 
			, MyVo vo  //사용자가 정의한 클래스타입인 경우, 객체의 속성(멤버변수)명과 동일한 이름의 파라미터값을 자동 저장 
			//인자로 받은 Model, Map, ModelMap 타입의 객체에 (스프링에서 제공-> Model, ModelMap, 자바-> Map)
			//key-value 형태로 데이터를 저장하면 JSP에서 ${key} 표현으로 value 사용 가능
			, @ModelAttribute("mv") MyVo mvo  //mvo 객체를 "mv"라는 이름으로 모델에 저장(추가) 
			// 브라우저에 입력한 파라미터 값이 MyVo타입 mvo에 담기고 "mv"라는 이름으로 저장됨
			// @ModelAttribute를 생략하면, 클래스명의 첫글자만 소문자로 변경한 이름으로 모델에 저장 -> 위에 MyVo vo 가 모델에 저장이 됐다는 뜻!
			, Map map
			, ModelMap modelMap
			, Model model) { 
		//Locale: 언어나 지역의 데이터, Model : jsp와 공유해야할 데이터를 저장 
		logger.info("Welcome home! The client locale is {}.", locale);
		logger.info("id : {}.", id);
		logger.info("myId : {}.", myId);
		logger.info("vo.myId : {}.", vo.getMyId());
		logger.info("vo.myName : {}.", vo.getMyName());
		//http://localhost:8000/exspring/home.do?myId=123&myName=James
		
//		model.addAttribute("mv", mvo);   == @ModelAttribute("mv") MyVo mvo 와 같은 효과 
		
		model.addAttribute("modelVal", "너부리");
		modelMap.addAttribute("modelMapVal", "포로리");
		map.put("mapVal", "보노보노"); 
		
		return "home"; 
		//핸들러(@RequestMapping 메서드)에서 문자열을 반환하면,
		//스프링을 그 문자열을 뷰이름으로 인식하고 (ViewResolver(servlet-context에 있음)를 사용하여) 처리
		// => /WEB-INF/views/home.jsp로 포워딩(이동이 됨)
	}
	
	//스프링이 핸들러(@RequestMapping 메서드)를 실행시키면, 
	//핸들러의 실행결과로서 스프링에게 반환(제공)해야 하는 정보
	//- 모델 : 응답에 포함되어야 하는 데이터(==JSP에서 응답을 출력할때 사용할 데이터)
	//- 뷰 : 응답 출력을 담당하는 객체(==JSP파일)
	
	//(1)모델과 뷰를 하나의 객체에 담아서 반환
	@RequestMapping("/test1.do")
	public ModelAndView aaa() {
		MyVo vo = new MyVo();
		vo.setMyId("a001"); 
		vo.setMyName("고길동");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("myVo", vo);  //vo객체를 "myVo"라는 이름으로 모델에 저장(추가) 
//		mav.setView( new InternalResourceView("/WEB-INF/views/test.jsp") );  //화면을 출력할 뷰 객체 생성. 인자에 경로를 주면 리소스라고 판단, 포워딩시키는 뷰.
		//InternalResourceView 객체는 지정한 경로의 파일로 이동(forward)시키는 뷰 
		// /webapp은 webapp폴더가 루트니까 지우면 된다.
		mav.setViewName("test");  // 뷰 객체 대신 뷰 이름으로 설정 가능.
		// 뷰이름만 반환-> ViewResolve에서 뷰 객체를 찾아 만들어달라. 문자열 앞뒤에 붙여서 경로를 스프링에게 준다.
		// 뷰이름을 알려줄땐 접두어와 확장자를 지우고 알려준다. 
		return mav;
	}
	
	//(2)뷰 정보(객체 or 이름)만 반환하는 방법. 가장널리쓰임.
	//	모델은 인자로 받은 Model, ModelMap, Map 객체에 저장. (스프링이 알아서! Map객체에 저장하는 것도 스프링이 해준거라 스프링이 알고 있음.)
	@RequestMapping("/test2.do")
	public String bbb( Map<String,Object> map ) {
		MyVo vo = new MyVo();
		vo.setMyId("a001"); 
		vo.setMyName("고길동");
		
		map.put("myVo", vo);  
//		return new InternalResourceView("/WEB-INF/views/test.jsp");  //뷰객체를 반환 
		return "test";  //뷰이름을 반환   
//		return mav;
	}
	
	//(3)모델 정보만 반환하는 방법
	//  반환한 객체를 클래스명(MyVo)의 첫글자만 소문자로 변경한 이름(myVo)으로 모델에 저장(추가)
	// 		모델에 저장되는 이름을 지정하고 싶으면, @ModelAttribute("이름")을 사용 
	//  뷰정보를 반환하지 않으면, (컨텍스트패스를 제외한) 요청주소에서 첫 /와 마지막 확장자를 제거한 문자열을 뷰 이름으로 사용  
	// - (컨텍스트패스:http://localhost:8000/exspring 삭제)
	//  "/exspring/test.do" -> "test" (뷰 이름으로 사용) 주소나 경로의 파일이름과 jsp파일이름이 같으면 이렇게 생략 가능
	@RequestMapping("/test.do")
	public MyVo ccc(  ) {
		MyVo vo = new MyVo();
		vo.setMyId("a001"); 
		vo.setMyName("고길동");
		return vo;
	}
	
	
	
	
}

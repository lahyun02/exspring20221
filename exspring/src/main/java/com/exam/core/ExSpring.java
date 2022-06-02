package com.exam.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ExSpring {
	public static void main(String[] args) {
		// 스프링 사용 안했을 경우 
//		MyApp ma = new MyApp();
//		ma.setMyService( new MyServiceKo() ); 
//		ma.say();
		
		// 클래스 패스 안에 있는 xml을 찾아서  ApplicationContext을 만들어줌.
		// 클래스 패스 기준이니까 경로명 중 -> /exspring/src/main/java/ 삭제 가능
		// 스프링(객체bean)컨테이너 == 어플리케이션컨텍스트(객체생성저장에 몇가지 추가됨) : 객체를 생성하여 저장 
		// com/exam/core/context.xml 설정파일의 내용대로 스프링컨테이너를 생성 
		ApplicationContext context = new ClassPathXmlApplicationContext("com/exam/core/context.xml");
		// 모든 객체의 종류를 받을 수 있도록 클래스타입을 ApplicationContext로 설정 
		
		// MyConfig.class 설정파일의 내용대로 스프링컨테이너를 생성 
//		ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
		
		MyApp app = (MyApp) context.getBean("ma"); 	//스프링(컨테이너)에 ma라는 이름으로 등록된 객체를 가져오기
		// 저장된 객체는 무수히 많은 타입으로 나타나기 때문에 무슨 객체를 말하는 지 알수 없어서 빨간줄.
		// 우리는 무슨 객체로 저장됐는지 클래스타입을 알고 있기 때문에 MyApp으로 강제 형변환
		app.say();
		
		// 스프링에 등록되어 있는 객체들의 목록 
		for (String bn : context.getBeanDefinitionNames()) {
			System.out.println( bn + " : " + context.getBean(bn).getClass().getName());
		}
		
	}
}

package com.exam.core;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 이 클래스의 객체를 생성하여 스프링에 ma라는 이름으로 등록
// 이름을 생략하면 클래스명의 첫글자만 소문자로 변경하여 이름으로 사용 
//@Component(value="ma") -> 속성값이 하나일때 value 생략 가능 
@Component("ma")
public class MyApp {
	//스프링에 등록된 객체들 중에서 이 변수의 타입에 맞는 객체를 주입(저장)
	@Autowired 	 //@Inject, @Resource도 @Autowired와 동일한 역할(작동방식은 약간 차이 존재)   // 자동연결 MyService 타입인 애를 찾아서 등록해줌. 
	// @Autowired(스프링에 포함), @Inject(자바표준) -> 타입 기준. 타입이 일치하는 아이 찾음.  @Resource(자바표준) -> 이름 기준. 이름이 일치하는 아이 찾음.
	// 타입이 맞는 객체가 여러개 있는 경우, 그 중 이름이 일치하는 객체를 주입 -> @Qualifier 또는 @Named으로 특정 객체 지정 가능
	// 이름지정을 생략하면, 변수명을 이름으로 사용(여기에선 myService 이름과 일치하는 객체 찾아 주입)
//	@Named("msk") // 이름 지정 myService변수에 자동으로 주입해라. 넣을 수 있는 두개 중에 이름이 mse/msk를 넣어라.
	//@Named("msk")를 없애면 오류남. -> MyApp이 의존할 수 있는 클래스의 애노테이션 이름이 두개가 있기때문!
	// MyServiceEn이나 MyServiceKo 둘 중에 남기고 싶은 이름이 등록된 하나의 애노테이션만 등록.
	private MyService myService;
	
	public MyService getMyService() {
		return myService;
	}

	public void setMyService(MyService myService) {
		this.myService = myService;
	}

	public void say() {
//		myService.getHelloMsg();
		System.out.println( myService.getHelloMsg() );
		// 1. myService.getHelloMsg() 실행 2. MyAdvice의 am메소드 실행 3. sysou으로 출력 
		System.out.println( myService.getByeMsg() );
	}
}

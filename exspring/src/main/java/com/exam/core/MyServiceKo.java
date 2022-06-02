package com.exam.core;

import org.springframework.stereotype.Component;

@Component("msk")
public class MyServiceKo implements MyService {

//	public String getHelloMsg() {
//		System.out.println("안녕하세요");
//		return null;
//	}
	
	@Override
	public String getHelloMsg() {
		return "안녕하세요";
	}

	@Override
	public String getByeMsg() {
		return "안녕히가세요";
	}

}

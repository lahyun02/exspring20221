package com.exam.core;

import org.springframework.stereotype.Component;

//@Component("mse")
public class MyServiceEn implements MyService {

	@Override
	public String getHelloMsg() {
		return "Hello";
	}

	@Override
	public String getByeMsg() {
		return "Bye";
	}

}

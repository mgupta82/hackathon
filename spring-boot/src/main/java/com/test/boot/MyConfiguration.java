package com.test.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MyConfiguration {
	
	@Bean
	public String message() {
		return "Hellow World";
	}
	
	@Bean
	//@Profile("prod")
	public MyMessage myMessage(@Value("${my.messageValue}") String messageValue) {
		MyMessage myMessage = new MyMessage();
		myMessage.setMessageValue(Integer.valueOf(messageValue));
		return myMessage;
	}
	
/*	@Bean
	@Profile("dev")	
	public MyMessage myMessageDev() {
		MyMessage myMessage = new MyMessage();
		myMessage.setMessageValue(0);
		return myMessage;		
	}*/

}

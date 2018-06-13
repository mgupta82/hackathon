package com.test.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableAutoConfiguration
//@Import(MyConfiguration.class)
//@ComponentScan
@SpringBootApplication
//@Controller
public class Application {
	
	@Value("${name}")
	String name;
	
	@Autowired
	String message;
	
	@Autowired
	MyMessage myMessage;
	
	@Autowired
	MyExample MyExample;
	
	@RequestMapping("/rest")
	public String home() {
		return "Hello World"+" name:"+name+" message:"+message+" myMessage:"+myMessage.getMessageValue()+" MyExample:"+MyExample.getMessageValue();
	}
	
	@RequestMapping("/viewresolver")
	public String viewResolver() {
		return "viewResolver";
	}
	@RequestMapping("/cat")
	@ResponseBody
	public Cat cat() {
		return new Cat("Athena","Bengal");
	}
	
	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
		
	}

}

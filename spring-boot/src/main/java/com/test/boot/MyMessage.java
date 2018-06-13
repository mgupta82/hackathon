package com.test.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class MyMessage {
	
	//@Value("${my.messageValue}")
	private int messageValue;

	public int getMessageValue() {
		return messageValue;
	}

	public void setMessageValue(int messageValue) {
		this.messageValue = messageValue;
	}

}

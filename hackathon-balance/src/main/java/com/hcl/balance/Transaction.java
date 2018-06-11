package com.hcl.balance;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Transaction {
	
	@Id
	private int id;
	
	private String inputMessage;
	
	private Date inputTimestamp;
	
	private String status;
	
	private String 	failureReasonCode;
	
	private Date outputTimestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInputMessage() {
		return inputMessage;
	}

	public void setInputMessage(String inputMessage) {
		this.inputMessage = inputMessage;
	}

	public Date getInputTimestamp() {
		return inputTimestamp;
	}

	public void setInputTimestamp(Date inputTimestamp) {
		this.inputTimestamp = inputTimestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFailureReasonCode() {
		return failureReasonCode;
	}

	public void setFailureReasonCode(String failureReasonCode) {
		this.failureReasonCode = failureReasonCode;
	}

	public Date getOutputTimestamp() {
		return outputTimestamp;
	}

	public void setOutputTimestamp(Date outputTimestamp) {
		this.outputTimestamp = outputTimestamp;
	}
	
	

}

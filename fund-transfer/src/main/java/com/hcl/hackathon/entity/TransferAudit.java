package com.hcl.hackathon.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TransferAudit {
	
	@Id
	private int id;
	
	private Date requestTime;
	
	private Date responseTime;
	
	private BigInteger fromAccount;
	
	private BigInteger toAccount;
	
	private String toAccountName;
	
	private BigInteger toBsb;
	
	private BigDecimal txnAmount;
	
	private String status;
	
	private String failureReasonCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public BigInteger getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(BigInteger fromAccount) {
		this.fromAccount = fromAccount;
	}

	public BigInteger getToAccount() {
		return toAccount;
	}

	public void setToAccount(BigInteger toAccount) {
		this.toAccount = toAccount;
	}

	public String getToAccountName() {
		return toAccountName;
	}

	public void setToAccountName(String toAccountName) {
		this.toAccountName = toAccountName;
	}

	public BigInteger getToBsb() {
		return toBsb;
	}

	public void setToBsb(BigInteger toBsb) {
		this.toBsb = toBsb;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
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



}

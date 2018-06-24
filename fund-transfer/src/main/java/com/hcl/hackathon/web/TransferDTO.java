package com.hcl.hackathon.web;

import java.math.BigDecimal;

public class TransferDTO {
	
	private String srvcType;
	
	private String fromAccount;
	
	private String toAccount;
	
	private String toAccountBsb;
	
	private BigDecimal txnAmount;

	public String getSrvcType() {
		return srvcType;
	}

	public void setSrvcType(String srvcType) {
		this.srvcType = srvcType;
	}

	public String getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}

	public String getToAccount() {
		return toAccount;
	}

	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}

	public String getToAccountBsb() {
		return toAccountBsb;
	}

	public void setToAccountBsb(String toAccountBsb) {
		this.toAccountBsb = toAccountBsb;
	}

	public BigDecimal getTxnAmount() {
		return txnAmount;
	}

	public void setTxnAmount(BigDecimal txnAmount) {
		this.txnAmount = txnAmount;
	}
	
	

}

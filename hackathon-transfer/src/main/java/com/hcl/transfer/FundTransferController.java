package com.hcl.transfer;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.hackathon.transfer.FundTransferRequest;
import com.hcl.hackathon.transfer.FundTransferResponse;
import com.hcl.hackathon.transfer.ObjectFactory;


@RestController
@RequestMapping("/api")
public class FundTransferController {
	
	private ObjectFactory ObjectFactory = new ObjectFactory();
	
	private ObjectMapper ObjectMapper = new ObjectMapper();
	
	@PostMapping("/fundtransfer")
	@ResponseBody
	public FundTransferResponse fundTransfer(@RequestBody FundTransferRequest request) {
		
		FundTransferResponse response = ObjectFactory.createFundTransferResponse();
		
		response.setMessageId(request.getMessageId());
		response.setStatusCode("000");
		response.setTxnId(new BigInteger("123"));
		
		return response;
		
	}

}

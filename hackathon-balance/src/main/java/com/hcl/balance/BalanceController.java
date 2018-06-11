package com.hcl.balance;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.hackathon.balance.BalanceRequest;
import com.hcl.hackathon.balance.BalanceResponse;
import com.hcl.hackathon.balance.ObjectFactory;
import com.mongodb.DB;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@RestController
@RequestMapping("/api")
@EnableResourceServer
public class BalanceController {
	
	private ObjectFactory ObjectFactory = new ObjectFactory();
	
	private ObjectMapper ObjectMapper = new ObjectMapper();
	
	@Autowired
	private TransactionRepository transactionRepository;
	

	@PostMapping("/balance")
	@ResponseBody
	public BalanceResponse getBalance(@RequestBody BalanceRequest balanceRequest) throws URISyntaxException, JsonParseException, JsonMappingException, IOException {
		Transaction transaction = new Transaction();
		transaction.setId(balanceRequest.getMessageId());
		transaction.setInputMessage(balanceRequest.getReqType());
		transaction.setInputTimestamp(new Date());
		
		//Call Core
		BalanceResponse balanceResponse = ObjectFactory.createBalanceResponse();
		try {
			RestTemplate template = new RestTemplate();
			URI uri = new URI("http://localhost:9999/core/accounts/"+balanceRequest.getAccount());
			String json = template.getForObject(uri,  String.class);
			Map<String,Object> map = ObjectMapper.readValue(json, Map.class);
			transaction.setOutputTimestamp(new Date());
			
			//Create Response
			balanceResponse.setMessageId(balanceRequest.getMessageId());
			balanceResponse.setAccount(balanceRequest.getAccount());
			balanceResponse.setBalance(new BigDecimal(map.get("balance").toString()));
			balanceResponse.setCurrency("AUD");
			transaction.setStatus("success");
		}catch (Exception ex) {
			transaction.setFailureReasonCode("1");
			transaction.setStatus("failed");
		}
		//persist transaction
		transactionRepository.save(transaction);
		
		return balanceResponse;
	}
	
}

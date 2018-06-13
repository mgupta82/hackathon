package com.hcl.balance;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.hackathon.balance.Account;
import com.hcl.hackathon.balance.BalanceRequest;
import com.hcl.hackathon.balance.BalanceResponse;
import com.hcl.hackathon.balance.Customer;
import com.hcl.hackathon.balance.ObjectFactory;

@RestController
@RequestMapping("/api")
//@EnableResourceServer
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
		transaction.setInputMessage("BalanceRequest");
		transaction.setInputTimestamp(new Date());
		
		//Call Core
		BalanceResponse balanceResponse = ObjectFactory.createBalanceResponse();
		try {
			RestTemplate template = new RestTemplate();
			URI uri = new URI("http://localhost:9999/core/accounts/"+balanceRequest.getCustomerId());
			String json = template.getForObject(uri,  String.class);
			Map<String,Object> map = ObjectMapper.readValue(json, Map.class);
			transaction.setOutputTimestamp(new Date());
			
			//Create Response
			balanceResponse.setMessageId(balanceRequest.getMessageId());
			Account account = new Account();
			account.setAccountId(new BigInteger("123"));
			account.setBalance(new BigDecimal(map.get("balance").toString()));
			account.setCurrency("AUD");

			Account account1 = new Account();
			account1.setAccountId(new BigInteger("123"));
			account1.setBalance(new BigDecimal(map.get("balance").toString()));
			account1.setCurrency("AUD");
			
			Customer customer = new Customer();
			customer.setCustomerId(balanceRequest.getCustomerId());
			Customer.Accounts accounts = new Customer.Accounts();
			customer.setAccounts(accounts);
			accounts.getAccount().add(account);
			accounts.getAccount().add(account1);
			balanceResponse.setCustomer(customer);
			

			transaction.setStatus("success");
		}catch (Exception ex) {
			transaction.setFailureReasonCode("1");
			transaction.setStatus("failed");
			balanceResponse.setStatusCode("123");
			balanceResponse.setFailureReason(ex.getMessage());
		}
		//persist transaction
		transactionRepository.save(transaction);

		return balanceResponse;
	}
	
}

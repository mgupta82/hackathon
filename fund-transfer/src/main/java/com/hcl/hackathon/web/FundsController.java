package com.hcl.hackathon.web;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hcl.hackathon.service.TransactionAuditService;
import com.hcl.hackathon.transfer.FundTransferRequest;
import com.hcl.hackathon.transfer.FundTransferResponse;
import com.hcl.hackathon.transfer.ObjectFactory;
import com.mongodb.MongoWriteException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class FundsController {
	
	@Autowired
	private TransactionAuditService service;
	
	private ObjectMapper ObjectMapper = new ObjectMapper();
	
	private ObjectFactory objectFactory = new ObjectFactory();
	
	@PostMapping("/fundtransfer")
	@ResponseBody
	public FundTransferResponse fundTransfer(@RequestBody FundTransferRequest request) throws URISyntaxException {
		FundTransferResponse response = objectFactory.createFundTransferResponse();
		
		//Audit Request
		try {
			service.createTransactionAuditRequest(request);
		}catch(MongoWriteException exception) {
			response.setStatusCode("333");
			response.setFailureReason("Duplicate Message");
		}catch(Exception exception) {
			response.setStatusCode("444");
			response.setFailureReason("System Error");			
		}
		
		//Integrate with Core API
		try {
			RestTemplate template = new RestTemplate();		
			URI uri = new URI("http://10.123.3.91:8681/core/fundtransfer/");
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setContentType(MediaType.APPLICATION_JSON);

			template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			template.getMessageConverters().add(new StringHttpMessageConverter());
			
			TransferDTO dto = new TransferDTO();
			
			dto.setSrvcType("BalanceTransfer");
			dto.setFromAccount(request.getFromAccount().toString());
			dto.setToAccount(request.getToAccount().toString());
			dto.setToAccountBsb(request.getToBsb().toString());
			dto.setTxnAmount(request.getTxnAmount());
			
			//HttpEntity<TransferDTO> entity = new HttpEntity<String>(dto,headers);
			String json = template.postForObject(uri, dto, String.class);
			Map<String,Object> map = ObjectMapper.readValue(json, Map.class);
			if(map.get("statusCode")!=null)
				response.setStatusCode(map.get("statusCode").toString());
			if(map.get("failureReason")!=null)
				response.setFailureReason(map.get("failureReason").toString());
			if(map.get("txnId")!=null)
				response.setTxnId(new BigInteger(map.get("txnId").toString()));
		}catch(Exception ex) {
			ex.printStackTrace();
			response.setStatusCode("555");
			response.setFailureReason("Transfer Failed");	
		}
		
		//Audit Response
		response.setMessageId(request.getMessageId());
		response.setTxnId(new BigInteger("123"));
		try {
		service.updateTransactionAuditResponse(response);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return response;		
		
	}

}

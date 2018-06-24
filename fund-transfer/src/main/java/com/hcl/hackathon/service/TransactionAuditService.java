package com.hcl.hackathon.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hcl.hackathon.dao.TransferAuditRepository;
import com.hcl.hackathon.entity.TransferAudit;
import com.hcl.hackathon.transfer.FundTransferRequest;
import com.hcl.hackathon.transfer.FundTransferResponse;
import com.mongodb.MongoWriteException;

@Service
public class TransactionAuditService {
	
	@Autowired
	TransferAuditRepository transferAuditRepository;
	
	public void createTransactionAuditRequest(FundTransferRequest request) throws Exception {
		TransferAudit audit = new TransferAudit();
		audit.setId(request.getMessageId());
		audit.setFromAccount(request.getFromAccount());
		audit.setToAccount(request.getToAccount());
		audit.setToAccountName(request.getToAccountName());
		audit.setToBsb(request.getToBsb());
		audit.setRequestTime(new Date());
		audit.setTxnAmount(request.getTxnAmount());
		transferAuditRepository.insert(audit);
	}
	
	public void updateTransactionAuditResponse(FundTransferResponse response) {
		Optional<TransferAudit> audit = transferAuditRepository.findById(response.getMessageId());
		if(audit.isPresent()) {
			audit.get().setResponseTime(new Date());
			audit.get().setStatus(response.getStatusCode());
			audit.get().setFailureReasonCode(response.getFailureReason());
			transferAuditRepository.save(audit.get());
		}
		
	}

}

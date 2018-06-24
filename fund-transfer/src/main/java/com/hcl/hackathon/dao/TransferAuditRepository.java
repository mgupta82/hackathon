package com.hcl.hackathon.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hcl.hackathon.entity.TransferAudit;

@Repository
public interface TransferAuditRepository extends MongoRepository<TransferAudit, Integer> {

}

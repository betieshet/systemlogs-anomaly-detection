package com.anomaly.endpoint.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anomaly.endpoint.model.SystemLog;

public interface SystemLogRepository extends MongoRepository<SystemLog, String> {
	
}

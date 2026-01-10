package com.anomaly.endpoint.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anomaly.endpoint.dto.LogRequest;
import com.anomaly.endpoint.dto.MlPredictionResponse;
import com.anomaly.endpoint.model.SystemLog;
import com.anomaly.endpoint.repository.SystemLogRepository;
import com.anomaly.endpoint.service.MlClientService;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final MlClientService mlClientService;
    private final SystemLogRepository repository;

    public LogController(
            MlClientService mlClientService,
            SystemLogRepository repository) {
        this.mlClientService = mlClientService;
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<MlPredictionResponse> ingest(
            @RequestBody LogRequest request) {

        MlPredictionResponse prediction = mlClientService.predict(request);

        SystemLog log = new SystemLog();
        log.setMetrics(request);
        log.setAnomaly(prediction.anomaly());
        log.setAnomalyScore(prediction.anomaly_score());
        log.setSeverity(prediction.severity_level());
        log.setLikelyCauses(prediction.likely_causes());

        repository.save(log);

        return ResponseEntity.ok(prediction);
    }
}

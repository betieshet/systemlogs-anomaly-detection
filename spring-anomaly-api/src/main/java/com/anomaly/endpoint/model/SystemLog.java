package com.anomaly.endpoint.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.anomaly.endpoint.dto.LogRequest;

@Document(collection = "system_logs")
public class SystemLog {

    @Id
    private String id;

    private LogRequest metrics;
    private boolean anomaly;
    private double anomalyScore;
    private String severity;
    private Object likelyCauses;
    private LocalDateTime createdAt = LocalDateTime.now();
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LogRequest getMetrics() {
		return metrics;
	}
	public void setMetrics(LogRequest metrics) {
		this.metrics = metrics;
	}
	public boolean isAnomaly() {
		return anomaly;
	}
	public void setAnomaly(boolean anomaly) {
		this.anomaly = anomaly;
	}
	public double getAnomalyScore() {
		return anomalyScore;
	}
	public void setAnomalyScore(double anomalyScore) {
		this.anomalyScore = anomalyScore;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public Object getLikelyCauses() {
		return likelyCauses;
	}
	public void setLikelyCauses(Object likelyCauses) {
		this.likelyCauses = likelyCauses;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}


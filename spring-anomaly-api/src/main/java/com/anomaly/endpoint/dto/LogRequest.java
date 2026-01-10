package com.anomaly.endpoint.dto;

public record LogRequest(
	    double cpuUtilization,
	    double memoryUsage,
	    double diskIo,
	    double networkLatency,
	    int processCount,
	    int threadCount,
	    long contextSwitches,
	    double cacheMissRate,
	    double temperature,
	    double powerConsumption,
	    long uptime
	) {}


package com.anomaly.endpoint.dto;

import java.util.List;
import java.util.Map;

public record MlPredictionResponse(
    boolean anomaly,
    double anomaly_score,
    String severity_level,
    List<Map<String, Object>> likely_causes
) {}


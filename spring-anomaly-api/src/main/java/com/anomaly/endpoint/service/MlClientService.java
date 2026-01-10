package com.anomaly.endpoint.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.anomaly.endpoint.dto.LogRequest;
import com.anomaly.endpoint.dto.MlPredictionResponse;

@Service
public class MlClientService {

    private final WebClient webClient;

    public MlClientService(WebClient webClient) {
        this.webClient = webClient;
    }

    public MlPredictionResponse predict(LogRequest request) {
        return webClient.post()
                .uri("/predict")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(MlPredictionResponse.class)
                .block();
    }
}


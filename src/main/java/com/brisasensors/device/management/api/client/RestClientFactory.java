package com.brisasensors.device.management.api.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RestClientFactory {

    private static final String BASE_URL = "http://localhost:8082";
    private static final int CONNECTION_TIMEOUT_SECONDS = 3;
    private static final int READ_TIMEOUT_SECONDS = 5;

    private final RestClient.Builder clientBuilder;

    public RestClient createRestClient() {
        return clientBuilder
                .baseUrl(BASE_URL)
                .requestFactory(generateClientHttpRequestFactory())
                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                    throw new DeviceMonitoringClientBadGatewayException(
                            "Error occurred while calling the Air Monitoring Service: " + response.getStatusCode());
                })
                .build();
    }

    private ClientHttpRequestFactory generateClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(Duration.ofSeconds(CONNECTION_TIMEOUT_SECONDS));
        factory.setReadTimeout(Duration.ofSeconds(READ_TIMEOUT_SECONDS));
        return factory;
    }
}

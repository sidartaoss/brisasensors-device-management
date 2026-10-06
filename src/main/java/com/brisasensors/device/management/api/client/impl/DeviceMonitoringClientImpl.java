package com.brisasensors.device.management.api.client.impl;

import com.brisasensors.device.management.api.client.DeviceMonitoringClient;
import com.brisasensors.device.management.api.client.RestClientFactory;
import com.brisasensors.device.management.api.model.DeviceMonitoringOutput;
import io.hypersistence.tsid.TSID;
import org.springframework.web.client.RestClient;

public class DeviceMonitoringClientImpl implements DeviceMonitoringClient {

    private final RestClient restClient;

    public DeviceMonitoringClientImpl(final RestClientFactory factory) {
        this.restClient = factory.createRestClient();
    }

    @Override
    public void enableMonitoring(TSID deviceId) {
        restClient.put()
                .uri("/api/devices/{deviceId}/monitoring/enable", deviceId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void disableMonitoring(TSID deviceId) {
        restClient.put()
                .uri("/api/devices/{deviceId}/monitoring/disable", deviceId)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public DeviceMonitoringOutput getDetail(TSID deviceId) {
        return restClient.get()
                .uri("/api/devices/{deviceId}/monitoring", deviceId)
                .retrieve()
                .body(DeviceMonitoringOutput.class);
    }
}

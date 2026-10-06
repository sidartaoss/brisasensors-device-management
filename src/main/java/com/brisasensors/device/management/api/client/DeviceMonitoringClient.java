package com.brisasensors.device.management.api.client;

import com.brisasensors.device.management.api.model.DeviceMonitoringOutput;
import io.hypersistence.tsid.TSID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange("/api/devices/{deviceId}/monitoring")
public interface DeviceMonitoringClient {

    @PutExchange("/enable")
    void enableMonitoring(@PathVariable("deviceId") TSID deviceId);

    @PutExchange("/disable")
    void disableMonitoring(@PathVariable("deviceId") TSID deviceId);

    @GetExchange
    DeviceMonitoringOutput getDetail(@PathVariable("deviceId") TSID deviceId);
}

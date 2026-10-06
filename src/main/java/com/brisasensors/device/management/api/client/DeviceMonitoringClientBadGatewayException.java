package com.brisasensors.device.management.api.client;

public class DeviceMonitoringClientBadGatewayException extends RuntimeException {

    public DeviceMonitoringClientBadGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeviceMonitoringClientBadGatewayException(String message) {
        this(message, null);
    }
}

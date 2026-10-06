package com.brisasensors.device.management.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceDetailOutput {
    private DeviceOutput device;
    private DeviceMonitoringOutput monitoring;
}

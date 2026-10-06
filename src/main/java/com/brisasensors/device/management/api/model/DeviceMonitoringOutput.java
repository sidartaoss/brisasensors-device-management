package com.brisasensors.device.management.api.model;

import io.hypersistence.tsid.TSID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMonitoringOutput {
    private TSID id;
    private Double lastCo2Ppm;
    private OffsetDateTime lastMeasuredAt;
    private Boolean enabled;
    private Boolean alerting;
}

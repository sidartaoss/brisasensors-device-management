package com.brisasensors.device.management.api.model;

import lombok.Data;

@Data
public class DeviceInput {
    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;
}

package com.brisasensors.device.management.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Device {
    @Id
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "BIGINT"))
    private DeviceId id;
    private String name;
    private String ip;
    private String location;
    private String protocol;
    private String model;
    private Boolean enabled;
}

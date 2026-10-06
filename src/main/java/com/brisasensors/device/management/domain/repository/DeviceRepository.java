package com.brisasensors.device.management.domain.repository;

import com.brisasensors.device.management.domain.model.Device;
import com.brisasensors.device.management.domain.model.DeviceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, DeviceId> {
}

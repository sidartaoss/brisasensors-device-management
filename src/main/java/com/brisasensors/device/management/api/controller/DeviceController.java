package com.brisasensors.device.management.api.controller;

import com.brisasensors.device.management.api.client.DeviceMonitoringClient;
import com.brisasensors.device.management.api.model.DeviceDetailOutput;
import com.brisasensors.device.management.api.model.DeviceInput;
import com.brisasensors.device.management.api.model.DeviceMonitoringOutput;
import com.brisasensors.device.management.api.model.DeviceOutput;
import com.brisasensors.device.management.common.IdGenerator;
import com.brisasensors.device.management.domain.model.Device;
import com.brisasensors.device.management.domain.model.DeviceId;
import com.brisasensors.device.management.domain.repository.DeviceRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceRepository deviceRepository;
    private final DeviceMonitoringClient deviceMonitoringClient;

    @GetMapping
    public Page<DeviceOutput> search(@PageableDefault Pageable pageable) {
        Page<Device> devices = deviceRepository.findAll(pageable);
        return devices.map(this::convertToModel);
    }

    @GetMapping("/{deviceId}")
    public DeviceOutput getOne(final @PathVariable("deviceId") TSID deviceId) {
        Device device = deviceRepository.findById(new DeviceId(deviceId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return convertToModel(device);
    }

    @GetMapping("/{deviceId}/detail")
    public DeviceDetailOutput getOneWithDetail(final @PathVariable("deviceId") TSID deviceId) {
        Device device = deviceRepository.findById(new DeviceId(deviceId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        DeviceMonitoringOutput deviceMonitoringOutput = deviceMonitoringClient.getDetail(deviceId);
        DeviceOutput deviceOutput = convertToModel(device);

        return DeviceDetailOutput.builder()
                .device(deviceOutput)
                .monitoring(deviceMonitoringOutput)
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeviceOutput create(final @RequestBody DeviceInput input) {
        Device device = Device.builder()
                .id(new DeviceId(IdGenerator.generateTSID()))
                .name(input.getName())
                .ip(input.getIp())
                .location(input.getLocation())
                .protocol(input.getProtocol())
                .model(input.getModel())
                .enabled(false)
                .build();

        device = deviceRepository.saveAndFlush(device);
        return convertToModel(device);
    }

    @PutMapping("/{deviceId}")
    public DeviceOutput update(
            final @PathVariable("deviceId") TSID deviceId,
            final @RequestBody DeviceInput input) {
        Device device = deviceRepository.findById(new DeviceId(deviceId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        device.setName(input.getName());
        device.setIp(input.getIp());
        device.setLocation(input.getLocation());
        device.setProtocol(input.getProtocol());
        device.setModel(input.getModel());

        device = deviceRepository.saveAndFlush(device);
        return convertToModel(device);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(final @PathVariable("deviceId") TSID deviceId) {
        Device device = deviceRepository.findById(new DeviceId(deviceId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        deviceRepository.delete(device);

        deviceMonitoringClient.disableMonitoring(deviceId);
    }

    @PutMapping("/{deviceId}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void enable(final @PathVariable("deviceId") TSID deviceId) {
        Device device = deviceRepository.findById(new DeviceId(deviceId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        device.setEnabled(true);
        deviceRepository.saveAndFlush(device);

        deviceMonitoringClient.enableMonitoring(deviceId);
    }

    @PutMapping("/{deviceId}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(final @PathVariable("deviceId") TSID deviceId) {
        Device device = deviceRepository.findById(new DeviceId(deviceId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        device.setEnabled(false);
        deviceRepository.saveAndFlush(device);

        deviceMonitoringClient.disableMonitoring(deviceId);
    }

    private DeviceOutput convertToModel(final Device device) {
        return DeviceOutput.builder()
                .id(device.getId().getValue())
                .name(device.getName())
                .ip(device.getIp())
                .location(device.getLocation())
                .protocol(device.getProtocol())
                .model(device.getModel())
                .enabled(device.getEnabled())
                .build();
    }
}

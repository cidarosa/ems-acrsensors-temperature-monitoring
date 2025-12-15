package com.github.cidarosa.acrsensor.temperature.monitoring.api.controller;

import com.github.cidarosa.acrsensor.temperature.monitoring.api.model.SensorAlertInputDTO;
import com.github.cidarosa.acrsensor.temperature.monitoring.api.model.SensorAlertOutputDTO;
import com.github.cidarosa.acrsensor.temperature.monitoring.domain.model.SensorAlert;
import com.github.cidarosa.acrsensor.temperature.monitoring.domain.model.SensorId;
import com.github.cidarosa.acrsensor.temperature.monitoring.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/sensors/{sensorId}/alert")
@RequiredArgsConstructor
public class SensorAlertController {

    private final SensorAlertRepository sensorAltertRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SensorAlertOutputDTO getSensorAlert(@PathVariable TSID sensorId) {

        SensorAlert sensorAlert = sensorAltertRepository.findById(new SensorId(sensorId))
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                );
        return convertToModel(sensorAlert);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public SensorAlertOutputDTO createOrUpdate(@PathVariable TSID sensorId,
                                               @RequestBody SensorAlertInputDTO inputDtO) {
        SensorAlert sensorAlert = findByIdOrDefault(sensorId);
        sensorAlert.setMaxTemperature(inputDtO.getMaxTemperature());
        sensorAlert.setMinTemperature(inputDtO.getMinTemperature());
        sensorAltertRepository.saveAndFlush(sensorAlert);

        return convertToModel(sensorAlert);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlert(@PathVariable TSID sensorId){

        SensorAlert sensorAlert = sensorAltertRepository.findById(new SensorId(sensorId))
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND));

        sensorAltertRepository.delete(sensorAlert);
    }

    private SensorAlert findByIdOrDefault(TSID sensorId) {
        return sensorAltertRepository.findById(new SensorId(sensorId))
                .orElse(SensorAlert.builder()
                        .id(new SensorId(sensorId))
                        .maxTemperature(null)
                        .minTemperature(null)
                        .build());
    }

    private SensorAlertOutputDTO convertToModel(SensorAlert sensorAlert) {
        return SensorAlertOutputDTO.builder()
                .id(sensorAlert.getId().getValue())
                .minTemperature(sensorAlert.getMinTemperature())
                .maxTemperature(sensorAlert.getMaxTemperature())
                .build();
    }


}

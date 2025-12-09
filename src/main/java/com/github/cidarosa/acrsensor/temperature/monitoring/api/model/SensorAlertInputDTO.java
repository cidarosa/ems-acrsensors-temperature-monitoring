package com.github.cidarosa.acrsensor.temperature.monitoring.api.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorAlertInputDTO {

    private Double maxTemperature;
    private Double minTemperature;
}

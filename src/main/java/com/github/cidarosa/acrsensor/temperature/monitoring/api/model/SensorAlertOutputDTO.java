package com.github.cidarosa.acrsensor.temperature.monitoring.api.model;

import io.hypersistence.tsid.TSID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorAlertOutputDTO {

    private TSID id;
    private Double maxTemperature;
    private Double minTemperature;

}

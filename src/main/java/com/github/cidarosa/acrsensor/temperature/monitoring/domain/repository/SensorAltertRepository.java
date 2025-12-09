package com.github.cidarosa.acrsensor.temperature.monitoring.domain.repository;

import com.github.cidarosa.acrsensor.temperature.monitoring.domain.model.SensorAlert;
import com.github.cidarosa.acrsensor.temperature.monitoring.domain.model.SensorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorAltertRepository extends JpaRepository<SensorAlert, SensorId> {
}

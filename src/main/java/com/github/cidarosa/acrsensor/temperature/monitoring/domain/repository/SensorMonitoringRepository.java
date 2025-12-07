package com.github.cidarosa.acrsensor.temperature.monitoring.domain.repository;

import com.github.cidarosa.acrsensor.temperature.monitoring.domain.model.SensorId;
import com.github.cidarosa.acrsensor.temperature.monitoring.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorMonitoringRepository extends JpaRepository<SensorMonitoring, SensorId> {
}

package com.bg.phrobe.repository;

import com.bg.phrobe.entities.Measurement;
import com.bg.phrobe.entities.Phone;
import org.springframework.data.repository.CrudRepository;

public interface MeasurementRepository extends CrudRepository<Measurement, Long> {
}

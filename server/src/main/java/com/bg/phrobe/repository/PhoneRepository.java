package com.bg.phrobe.repository;

import com.bg.phrobe.entities.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by bg
 */

public interface PhoneRepository extends CrudRepository<Phone, Long> {
}

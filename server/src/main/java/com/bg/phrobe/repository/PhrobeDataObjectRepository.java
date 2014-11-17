package com.bg.phrobe.repository;

import com.bg.phrobe.entities.PhrobeDataObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by bg on 2014.11.17..
 */
public interface PhrobeDataObjectRepository extends CrudRepository<PhrobeDataObject, Long> {
    @Query("SELECT p FROM PhrobeDataObject p WHERE p.apiKey = :apikey ORDER BY p.timestamp DESC")
    List<PhrobeDataObject> findByApiKey(@Param("apikey") String apiKey, Pageable pageable);
}
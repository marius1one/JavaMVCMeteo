package com.example.javamvcmeteo.repository;

import com.example.javamvcmeteo.entities.ForecastEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ForecastRepository extends CrudRepository<ForecastEntity, Integer>{
    List<ForecastEntity> findAll();


}


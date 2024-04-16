package com.example.javamvcmeteo.repository;

import com.example.javamvcmeteo.entities.ForecastEntity;
import com.example.javamvcmeteo.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ForecastRepository extends CrudRepository<ForecastEntity, Integer> {
    List<ForecastEntity> findAll();


    List<ForecastEntity> findByUser(UserEntity currentUser);

    boolean existsByDateAndCityAndUser(String date, String city, UserEntity user);
}


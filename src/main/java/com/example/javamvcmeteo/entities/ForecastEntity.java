package com.example.javamvcmeteo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class ForecastEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String city;
    public String date;

    public String temperature;
    public ForecastEntity() {
    }

public ForecastEntity(String city, String date, String temperature) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
    }


}

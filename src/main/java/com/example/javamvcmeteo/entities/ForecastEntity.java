package com.example.javamvcmeteo.entities;

import jakarta.persistence.*;

@Entity

public class ForecastEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public String city;
    public String date;

    public String temperature;
    public String conditionCode;
    boolean alreadyExists;
    @ManyToOne
    private UserEntity user;
    public ForecastEntity() {
    }

public ForecastEntity(String city, String date, String temperature, String conditionCode) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
        this.conditionCode = conditionCode;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity currentUser) {
        this.user = currentUser;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }


}

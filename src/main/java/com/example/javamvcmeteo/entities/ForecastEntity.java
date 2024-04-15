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
    @ManyToOne
    private UserEntity user;
    public ForecastEntity() {
    }

public ForecastEntity(String city, String date, String temperature) {
        this.city = city;
        this.date = date;
        this.temperature = temperature;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity currentUser) {
        this.user = currentUser;
    }
}

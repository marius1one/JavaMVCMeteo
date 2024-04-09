package com.example.javamvcmeteo.models;

public class ForecastModel {

    public String dateTime;
    public Double temperature;
    public int windSpeed;

    public ForecastModel(String dateTime, double temperature, int windSpeed) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
    }


}

package com.example.javamvcmeteo.models;

public class ForecastModel {
    public String dateTime;
    public double temperature;

    public ForecastModel(String dateTime, double temperature) {
        this.dateTime = dateTime;
        this.temperature = temperature;
    }
}

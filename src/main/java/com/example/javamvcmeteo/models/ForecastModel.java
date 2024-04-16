package com.example.javamvcmeteo.models;

public class ForecastModel {

    public String dateTime;
    public Double temperature;
    public int windSpeed;
    public String conditionCode;
    private boolean alreadyExists;

    public ForecastModel(String dateTime, double temperature, int windSpeed, String conditionCode) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.conditionCode = conditionCode;
    }
public boolean isAlreadyExists() {
        return alreadyExists;
    }

    public void setAlreadyExists(boolean alreadyExists) {
        this.alreadyExists = alreadyExists;
    }

}

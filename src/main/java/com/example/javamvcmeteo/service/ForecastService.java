package com.example.javamvcmeteo.service;

import com.example.javamvcmeteo.entities.UserEntity;
import com.example.javamvcmeteo.models.ForecastModel;
import com.example.javamvcmeteo.models.Root;
import com.example.javamvcmeteo.repository.ForecastRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class ForecastService {
    private final ForecastRepository forecastRepository;

    public ForecastService(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    public static String GetMeteoForecastsJson(String city) throws IOException {
        URL url = new URL("https://api.meteo.lt/v1/places/" + city + "/forecasts/long-term");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        String text = "";
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            text += scanner.nextLine();
        }
        scanner.close();
        return text;
    }

    public static ArrayList<ForecastModel> getForecastsWithDate(String city, String date) throws IOException {
        var forecasts = new ArrayList<ForecastModel>();

        if (city != null && !city.equals("")) {
            var meteoForecastsJson = GetMeteoForecastsJson(city);
            Root meteoObj = GetObjectFromJson(meteoForecastsJson);
            for (var item : meteoObj.forecastTimestamps) {
                if (date == null || item.forecastTimeUtc.startsWith(date)) {
                    var row = new ForecastModel(item.forecastTimeUtc, item.airTemperature, item.windSpeed, item.conditionCode);
                    forecasts.add(row);
                }
            }
        }

        return forecasts;
    }

    public ArrayList<ForecastModel> getForecasts(String city, String date, UserEntity user) throws IOException {
        var forecasts = new ArrayList<ForecastModel>();

        if (city != null && !city.equals("")) {
            var meteoForecastsJson = GetMeteoForecastsJson(city);
            Root meteoObj = GetObjectFromJson(meteoForecastsJson);
            for (var item : meteoObj.forecastTimestamps) {
                if (date == null || item.forecastTimeUtc.startsWith(date)) {
                    var row = new ForecastModel(item.forecastTimeUtc, item.airTemperature, item.windSpeed, item.conditionCode);
                    boolean alreadyExists = forecastRepository.existsByDateAndCityAndUser(item.forecastTimeUtc, city, user);
                    row.setAlreadyExists(alreadyExists);
                    forecasts.add(row);
                }
            }
        }

        return forecasts;
    }

    private static Root GetObjectFromJson(String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Root meteoObj = om.readValue(json, Root.class);
        return meteoObj;
    }

    public static String getTemperature(String city, String date) throws IOException {
        var json = GetMeteoForecastsJson(city);
        var object = GetObjectFromJson(json);

        for (var item : object.forecastTimestamps) {
            if (item.forecastTimeUtc.equals(date)) {
                return Double.toString(item.airTemperature);
            }
        }

        return "";
    }

    public static String getCondition(String city, String date) throws IOException {
        var json = GetMeteoForecastsJson(city);
        var object = GetObjectFromJson(json);

        for (var item : object.forecastTimestamps) {
            if (item.forecastTimeUtc.equals(date)) {
                return item.conditionCode;
            }
        }

        return "";
    }


    public ArrayList<ForecastModel> getForecastsVilnius(String city) throws IOException {
        var forecasts = new ArrayList<ForecastModel>();
        var meteoForecastsJson = GetMeteoForecastsJson(city);
        Root meteoObj = GetObjectFromJson(meteoForecastsJson);
        for (var item : meteoObj.forecastTimestamps) {
            var row = new ForecastModel(item.forecastTimeUtc, item.airTemperature, item.windSpeed, item.conditionCode);
            forecasts.add(row);
        }
        return forecasts;
    }


}
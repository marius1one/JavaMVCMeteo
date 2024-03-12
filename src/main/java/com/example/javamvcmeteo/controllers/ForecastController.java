package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.models.Coordinates;
import com.example.javamvcmeteo.models.ForecastModel;
import com.example.javamvcmeteo.models.ForecastTimestamp;
import com.example.javamvcmeteo.models.Root;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class ForecastController {
    @GetMapping("/")
    public ModelAndView index() throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");

        var forecasts = getForecastModels();
        var meteoForecastsJson = getMeteoForecastsJson();

        var objectFromJson = getForecastsFromJson(getMeteoForecastsJson());

        modelAndView.addObject("forecasts", forecasts);

        return modelAndView;
    }

    public static String getMeteoForecastsJson() throws IOException {
        URL url = new URL("https://api.meteo.lt/v1/places/vilnius/forecasts/long-term");

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

    private static Root getForecastsFromJson(String text) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Root obj = objectMapper.readValue(text, Root.class);
        return obj;

    }


    private static ArrayList<ForecastModel> getForecastModels() throws IOException {
        var forecasts = new ArrayList<ForecastModel>();
        ObjectMapper objectMapper = new ObjectMapper();
        Root meteoObj = objectMapper.readValue(getMeteoForecastsJson(), Root.class);

        for (var item: meteoObj.forecastTimestamps) {
            var row = new ForecastModel(item.forecastTimeUtc, item.airTemperature);
            forecasts.add(row);
        }

        return forecasts;
    }


}

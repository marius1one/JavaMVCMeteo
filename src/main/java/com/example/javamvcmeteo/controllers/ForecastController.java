package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.models.ForecastModel;
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
        System.out.println(meteoForecastsJson);

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

    private static ArrayList<ForecastModel> getForecastModels() {
        var forecasts = new ArrayList<ForecastModel>();
        var row1 = new ForecastModel("2024-03-12 11:00", 1.0);
        var row2 = new ForecastModel("2024-03-12 12:00", 4.2);
        var row3 = new ForecastModel("2024-03-12 13:00", 6.4);

        forecasts.add(row1);
        forecasts.add(row2);
        forecasts.add(row3);
        return forecasts;
    }
}

package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.models.ForecastModel;
import com.example.javamvcmeteo.models.IndexModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import static com.example.javamvcmeteo.service.ForecastService.getForecasts;
import static com.example.javamvcmeteo.service.ForecastService.getForecastsWithDate;

@Controller
public class ForecastController {

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) String city, @RequestParam(required = false) String date) throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");
        var indexModel = new IndexModel();
        indexModel.city = city;
        indexModel.date = date;

        var forecasts = getForecastsWithDate(city, date);
        indexModel.forecasts = forecasts;


        modelAndView.addObject("indexModel", indexModel);

        return modelAndView;
    }

    @GetMapping("/siandien")
    public ModelAndView siandien(@RequestParam(required = false) String city) throws IOException {
        ModelAndView modelAndView = new ModelAndView("siandien");

        var indexModel = new IndexModel();
        indexModel.city = city;

        if (indexModel.city == null || indexModel.city.equals("")) {
            city = "vilnius";
            indexModel.city = "Vilnius";
        }
        var forecasts = getForecasts(city);


        indexModel.forecasts = forecasts;
        ForecastModel forecastModel;

        forecastModel = new ForecastModel(indexModel.forecasts.get(0).dateTime, indexModel.forecasts.get(0).temperature, indexModel.forecasts.get(0).windSpeed);


        modelAndView.addObject("indexModel", indexModel);

        return modelAndView;
    }

    @GetMapping("/oraiDidziuosiuoseMiestuose")

    public ModelAndView oraiDidziuosiuoseMiestuose() {
        ModelAndView modelAndView = new ModelAndView("oraiDidziuosiuoseMiestuose");
        var indexModel = new IndexModel();
        var indexModel1 = new IndexModel();
        var indexModel2 = new IndexModel();
        indexModel.city = "vilnius";
        indexModel1.city = "kaunas";
        indexModel2.city = "klaipeda";
        return modelAndView;
    }





}
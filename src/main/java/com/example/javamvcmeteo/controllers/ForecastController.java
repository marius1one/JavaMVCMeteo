package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.models.ForecastModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ForecastController {
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");

        var forecasts = new ArrayList<ForecastModel>();
        var row1 = new ForecastModel("2024-03-12 11:00", 1.0);
        var row2 = new ForecastModel("2024-03-12 12:00", 4.2);
        var row3 = new ForecastModel("2024-03-12 13:00", 6.4);

        forecasts.add(row1);
        forecasts.add(row2);
        forecasts.add(row3);

        modelAndView.addObject("forecasts", forecasts);

        return modelAndView;
    }
}

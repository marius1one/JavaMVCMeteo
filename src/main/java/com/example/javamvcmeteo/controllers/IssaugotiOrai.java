package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.entities.ForecastEntity;
import com.example.javamvcmeteo.repository.ForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IssaugotiOrai {
    @Autowired
    private ForecastRepository forecastRepository;

    @GetMapping("/issaugotiOrai")
    public String rodytiIssaugotusOrus(Model model) {
        List<ForecastEntity> forecasts = forecastRepository.findAll();
        model.addAttribute("forecasts", forecasts);
        return "issaugotiOrai";
    }
}

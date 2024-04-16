package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.entities.UserEntity;
import com.example.javamvcmeteo.models.ForecastModel;
import com.example.javamvcmeteo.models.IndexModel;
import com.example.javamvcmeteo.repository.UserRepository;
import com.example.javamvcmeteo.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
public class ForecastController {
    @Autowired
    ForecastService forecastService;
    @Autowired
    UserRepository userRepository;


    private UserEntity getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepository.findByUsername(username);
        }
        return null;
    }

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false) String city, @RequestParam(required = false) String date) throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");
        var indexModel = new IndexModel();
        indexModel.city = city;
        indexModel.date = date;

        UserEntity currentUser = getCurrentUser();
        var forecasts = forecastService.getForecasts(city, date, currentUser);


        indexModel.forecasts = forecasts;


        modelAndView.addObject("indexModel", indexModel);

        return modelAndView;
    }

    @GetMapping("/siandien")
    public ModelAndView siandien(@RequestParam(required = false) String city) throws IOException {
        ModelAndView modelAndView = new ModelAndView("siandien");

        var indexModel = new IndexModel();
        indexModel.city = city;

        if (indexModel.city == null || indexModel.city.isEmpty()) {
            city = "vilnius";
            indexModel.city = "Vilnius";
        }
        var forecasts = forecastService.getForecastsVilnius(city);


        indexModel.forecasts = forecasts;
        ForecastModel forecastModel;

        forecastModel = new ForecastModel(indexModel.forecasts.get(0).dateTime, indexModel.forecasts.get(0).temperature,
                indexModel.forecasts.get(0).windSpeed, indexModel.forecasts.get(0).conditionCode);


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
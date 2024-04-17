package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.entities.UserEntity;
import com.example.javamvcmeteo.models.IndexModel;
import com.example.javamvcmeteo.repository.UserRepository;
import com.example.javamvcmeteo.service.ForecastService;
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
    final
    ForecastService forecastService;
    final
    UserRepository userRepository;

    public ForecastController(ForecastService forecastService, UserRepository userRepository) {
        this.forecastService = forecastService;
        this.userRepository = userRepository;
    }


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


        indexModel.forecasts = forecastService.getForecasts(city, date, currentUser);


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


        indexModel.forecasts = forecastService.getForecastsVilnius(city);


        modelAndView.addObject("indexModel", indexModel);

        return modelAndView;
    }

    @GetMapping("/oraiDidziuosiuoseMiestuose")

    public ModelAndView oraiDidziuosiuoseMiestuose() throws IOException {
        ModelAndView modelAndView = new ModelAndView("oraiDidziuosiuoseMiestuose");
        var indexModel = new IndexModel();
        var indexModel1 = new IndexModel();
        var indexModel2 = new IndexModel();
        indexModel.city = "Vilnius";
        indexModel.forecasts = forecastService.getForecastsVilnius(indexModel.city);

        indexModel1.city = "Kaunas";
        indexModel1.forecasts = forecastService.getForecastsVilnius(indexModel1.city);

        indexModel2.city = "Klaipeda";
        indexModel2.forecasts = forecastService.getForecastsVilnius(indexModel2.city);

        modelAndView.addObject("indexModel", indexModel);
        modelAndView.addObject("indexModel1", indexModel1);
        modelAndView.addObject("indexModel2", indexModel2);

        return modelAndView;
    }

    @GetMapping("/rytoj")
    public ModelAndView rytoj(@RequestParam(required = false) String city) throws IOException {
        ModelAndView modelAndView = new ModelAndView("rytoj");

        UserEntity currentUser = getCurrentUser();

        var indexModel = new IndexModel();
        indexModel.city = city;

        if (indexModel.city == null || indexModel.city.isEmpty()) {
            city = "vilnius";
            indexModel.city = "Vilnius";
        }


        indexModel.forecasts = forecastService.getForecastsForTomorrow(city, currentUser);


        modelAndView.addObject("indexModel", indexModel);

        return modelAndView;
    }




}
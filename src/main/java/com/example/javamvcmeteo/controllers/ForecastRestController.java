package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.entities.ForecastEntity;
import com.example.javamvcmeteo.entities.UserEntity;
import com.example.javamvcmeteo.models.ForecastSaveModel;
import com.example.javamvcmeteo.repository.ForecastRepository;
import com.example.javamvcmeteo.repository.UserRepository;
import com.example.javamvcmeteo.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ForecastRestController {
    @Autowired
    private ForecastRepository forecastRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/saveData")
    public void saveData(@RequestBody ForecastSaveModel model) throws IOException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity currentUser = userRepository.findByUsername(userDetails.getUsername());
        var date = model.dateTime;
        var city = model.city;
        var temperature = ForecastService.getTemperature(city, date);

        var forecastEntity = new ForecastEntity(city, date, temperature);
        forecastEntity.setUser(currentUser);
        forecastRepository.save(forecastEntity);


    }
}

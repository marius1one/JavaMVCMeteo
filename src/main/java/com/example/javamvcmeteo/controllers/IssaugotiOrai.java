package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.entities.ForecastEntity;
import com.example.javamvcmeteo.entities.UserEntity;
import com.example.javamvcmeteo.repository.ForecastRepository;
import com.example.javamvcmeteo.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IssaugotiOrai {
    private final ForecastRepository forecastRepository;
    private final UserRepository userRepository;

    public IssaugotiOrai(ForecastRepository forecastRepository, UserRepository userRepository) {
        this.forecastRepository = forecastRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/issaugotiOrai")
    public String rodytiIssaugotusOrus(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity currentUser = userRepository.findByUsername(userDetails.getUsername());

        List<ForecastEntity> forecasts = forecastRepository.findByUser(currentUser);
        model.addAttribute("forecasts", forecasts);
        return "issaugotiOrai";
    }
}

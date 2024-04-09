package com.example.javamvcmeteo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Prisijungimas {
    @GetMapping("/login")
    public String login() {
        return "login";}

}
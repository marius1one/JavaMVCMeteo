/*package com.example.javamvcmeteo.controllers;

import com.example.javamvcmeteo.models.IndexModel;
import com.example.javamvcmeteo.models.RootModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class CitiesController {

    @GetMapping("/mst")
    public ModelAndView index() throws IOException {
        ModelAndView modelAndView = new ModelAndView("index");

        ArrayList<IndexModel> miestai = getCities();

        modelAndView.addObject("miestai", miestai);
        return modelAndView;

    }



    private static ArrayList<IndexModel> getCities() throws IOException {
        ArrayList<IndexModel> miestai = new ArrayList<>();
        RootModel[] meteoCitiesJson = getPlaceObjectFromJson(getCitiesJson());
        for (var item : meteoCitiesJson) {
            var row = new IndexModel(item.name);
            row.city = item.name;
            miestai.add(row);
        }
        return miestai;
    }

    public static String getCitiesJson() throws IOException {
        URL url = new URL("https://api.meteo.lt/v1/places");

        HttpURLConnection connect = (HttpURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        connect.connect();

        String txt = "";
        Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            txt += scanner.nextLine();
        }
        scanner.close();
        return txt;
    }


    private static RootModel[] getPlaceObjectFromJson(String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        RootModel[] placeObj = om.readValue(json, RootModel[].class);
        return placeObj;
    }

}*/


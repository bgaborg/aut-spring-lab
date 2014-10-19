package com.bg.phrobe.controllers;

import com.bg.phrobe.entities.Measurement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    @RequestMapping("/")
    String home() {
        return "redirect:/dash";
    }

    @RequestMapping("/test")
    @ResponseBody
    List<Measurement> getMeasurements() {
        List<Measurement> measurements = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            Measurement m = new Measurement();
            m.setGpsAccuracy(rand.nextFloat() * 10);
            m.setSignalStrength(rand.nextFloat() * 10);
            m.setDate(new Date());
            measurements.add(m);
        }

        return measurements;
    }

}
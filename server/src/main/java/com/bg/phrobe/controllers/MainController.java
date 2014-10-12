package com.bg.phrobe.controllers;

import com.bg.phrobe.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    String home() {
        return "index";
    }

}
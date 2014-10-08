package com.bg.phrobe.controllers;

import com.bg.phrobe.entities.Phone;
import com.bg.phrobe.entities.User;
import com.bg.phrobe.repository.PhoneRepository;
import com.bg.phrobe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestApp {

    @RequestMapping("/")
    String home() {
        return "index";
    }

}
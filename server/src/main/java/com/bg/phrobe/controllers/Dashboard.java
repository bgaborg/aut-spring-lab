package com.bg.phrobe.controllers;

import com.bg.phrobe.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bg on 2014.10.13..
 */
@Controller
public class Dashboard {

    @Autowired
    PhoneRepository phoneRepository;

    @RequestMapping("/dash")
    public ModelAndView index(){
        ModelAndView mV = new ModelAndView();

        mV.addObject("phones", phoneRepository.findAll());

        mV.setViewName("dashboard/index");

        return mV;
    }
}

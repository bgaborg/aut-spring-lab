package com.bg.phrobe.controllers;

import com.bg.phrobe.entities.Phone;
import com.bg.phrobe.repository.PhoneRepository;
import com.bg.phrobe.repository.UserRepository;
import com.bg.phrobe.util.PropertiesHolder;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by bg
 */
@Controller
public class PhoneController {

    List<String> messages = new ArrayList<>();

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @RequestMapping(value = "/addphone", params = {"api", "nick"})
    public ModelAndView addPhone(@RequestParam("api") String api, @RequestParam("nick") String nick) {
        Phone phone = new Phone(api, nick);
        phoneRepository.save(phone);
        messages.add("Random phone added: " + phone);

        return listPhone();
    }

    @RequestMapping(value = "/addRandomPhone")
    public ModelAndView addRandomPhone() {
        SecureRandom random = new SecureRandom();
        String api = new BigInteger(130, random).toString(32);
        String name = UUID.randomUUID().toString();
        Phone phone = new Phone(api, name);
        phoneRepository.save(phone);

        messages.add("Random phone added: " + phone);
        return listPhone();
    }

    @RequestMapping(value = "/listphone")
    public ModelAndView listPhone() {
        ModelAndView mV = new ModelAndView();

        List<Phone> phoneList = (List<Phone>) phoneRepository.findAll();
        mV.addObject("phones", phoneList);

        mV.addObject("messages", new ArrayList<>(messages));
        messages.clear();

        mV.setViewName("listphones");

        return mV;
    }

    @RequestMapping(value = "/notifyPhone")
    public ModelAndView notifyPhone(@RequestParam String apiKey) {
        Sender sender = new Sender(PropertiesHolder.GCM_API_KEY);
        Message message = new Message.Builder()
                .addData("message", "Sent from PhoneController")
                .build();
        try {
            final Result result = sender.send(message, apiKey, 5);

            messages.add("Notification send result: " + result.toString());
        } catch (IOException e) {
            e.printStackTrace();
            messages.add("Error during sending notification: " + e.getMessage());
        }

        return listPhone();
    }
}
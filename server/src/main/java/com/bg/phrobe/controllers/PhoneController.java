package com.bg.phrobe.controllers;

import com.bg.phrobe.entities.PhrobeDataObject;
import com.bg.phrobe.entities.Measurement;
import com.bg.phrobe.entities.Phone;
import com.bg.phrobe.repository.PhoneRepository;
import com.bg.phrobe.repository.PhrobeDataObjectRepository;
import com.bg.phrobe.repository.UserRepository;
import com.bg.phrobe.util.ListWrapper;
import com.bg.phrobe.util.PropertiesHolder;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by bg
 */
@Controller
@RequestMapping("/phones")
public class PhoneController {

    @Autowired
    PhrobeDataObjectRepository phrobeDataObjectRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @RequestMapping(value = "/getMeasurements", method = RequestMethod.GET)
    @ResponseBody
    List<Measurement> getMeasurements() {
        List<Measurement> measurements = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            Measurement m = new Measurement();
            m.setGpsAccuracy(rand.nextFloat() * 10);
            m.setSignalStrength(rand.nextFloat() * 10);
            m.setDate(new Date());
            m.setId((long) i);
            measurements.add(m);
        }

        return measurements;
    }

    @RequestMapping(value = "/addphone", params = {"api", "nick"})
    @ResponseBody
    public String addPhone(@RequestParam("api") String api, @RequestParam("nick") String nick) {
        Phone phone = new Phone(api, nick);
        phoneRepository.save(phone);

        return "Success.";
    }

    @RequestMapping(value = "/addRandomPhone")
    @ResponseBody
    public String addRandomPhone() {
        SecureRandom random = new SecureRandom();
        String api = new BigInteger(130, random).toString(32);
        String name = UUID.randomUUID().toString();
        Phone phone = new Phone(api, name);
        phoneRepository.save(phone);

        return "Random phone added.";
    }

    @RequestMapping(value = "/all")
    @ResponseBody
    public Iterable<Phone> getPhones() {
        return phoneRepository.findAll();
    }

    @RequestMapping(value = "/notifyPhone")
    @ResponseBody
    public ServerRespose notifyPhone(@RequestParam String apiKey, @RequestParam String msg,
                                     @RequestParam String serverIp) {
        Sender sender = new Sender(PropertiesHolder.GCM_API_KEY);
        Message message = new Message.Builder()
                .addData("message", msg)
                .addData("serverIp", serverIp)
                .build();
        try {
            final Result result = sender.send(message, apiKey, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ServerRespose("Phone notified");
    }

    @RequestMapping(value = "/getPhrobeData", method = RequestMethod.GET)
    @ResponseBody
    ListWrapper<PhrobeDataObject> getPhrobeData(@RequestParam String apiKey) {
        ListWrapper<PhrobeDataObject> listWrapper = new ListWrapper<>();
        Pageable pageable = new PageRequest(0, 100);
        listWrapper.list = phrobeDataObjectRepository.findByApiKey(apiKey, pageable);
        return listWrapper;
    }

    @RequestMapping(value = "/addMetrics", method = RequestMethod.POST, produces = "text/plain")
    @ResponseBody
    public String addMetrics(@RequestBody PhrobeDataObject phrobeDataObject) throws IOException {
        System.out.println(phrobeDataObject);
        phrobeDataObjectRepository.save(phrobeDataObject);
        return "data saved";
    }

    public static class ServerRespose {
        public String status;

        ServerRespose(String status) {
            this.status = status;
        }
    }
}
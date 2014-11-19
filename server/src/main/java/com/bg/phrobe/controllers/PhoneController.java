package com.bg.phrobe.controllers;

import com.bg.phrobe.dto.StatsDto;
import com.bg.phrobe.entities.PhrobeDataObject;
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
    StatsDto getMeasurements() {
        StatsDto statsDto = new StatsDto();
        StatsDto.Nv3Displayable<Float> signalStrength = new StatsDto.Nv3Displayable<>();
        signalStrength.key = "signalStrength";
        StatsDto.Nv3Displayable<Float> gpsAccuracy = new StatsDto.Nv3Displayable<>();
        gpsAccuracy.key = "gpsAccuracy";

        statsDto.stats.add(signalStrength);
        statsDto.stats.add(gpsAccuracy);

        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            signalStrength.values.add(rand.nextFloat() * 10);
            gpsAccuracy.values.add(rand.nextFloat() * 10);
        }

        return statsDto;
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
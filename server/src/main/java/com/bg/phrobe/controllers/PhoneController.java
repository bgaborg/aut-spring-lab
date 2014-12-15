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

import javax.swing.text.Position;
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

    public static class Position{
        public double latitude = 0;
        public double longitude = 0;
        public Date date = new Date();

        public Position(double latitude, double longitude, Date date) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.date = date;
        }
    }

    @RequestMapping(value = "/getPositions/{id}", method = RequestMethod.GET)
    @ResponseBody
    List<Position> getPositions(@PathVariable Long id) {
        List<Position> positions = new ArrayList<>();
        Phone phone = phoneRepository.findOne(id);
        Pageable pageable = new PageRequest(0, 1);
        List<PhrobeDataObject> phrobeDataObjects = phrobeDataObjectRepository.findByApiKey(phone.getApi_key(), pageable);

        for(PhrobeDataObject pdo : phrobeDataObjects){
            positions.add(new Position(pdo.location.latitude, pdo.location.longitude, new Date(pdo.timestamp)));
        }

        return positions;
    }


    @RequestMapping(value = "/getMeasurements/{id}", method = RequestMethod.GET)
    @ResponseBody
    StatsDto getMeasurements(@PathVariable Long id) {
        StatsDto statsDto = new StatsDto();
        StatsDto.Nv3Displayable<Integer> signalStrength = new StatsDto.Nv3Displayable<>();
        signalStrength.key = "signalStrength";
        StatsDto.Nv3Displayable<Double> gpsAccuracy = new StatsDto.Nv3Displayable<>();
        gpsAccuracy.key = "gpsAccuracy";

        statsDto.stats.add(signalStrength);
        statsDto.stats.add(gpsAccuracy);

        Pageable pageable = new PageRequest(0, 100);
        Phone phone = phoneRepository.findOne(id);
        if (phone == null) {
            return statsDto;
        }
        List<PhrobeDataObject> phrobeDataObjects = phrobeDataObjectRepository.findByApiKey(phone.getApi_key(), pageable);

        for (PhrobeDataObject pDO : phrobeDataObjects) {
            signalStrength.values.add(pDO.signalStrength.signalStrength*10);
            signalStrength.dates.add(new Date(pDO.timestamp));
            gpsAccuracy.values.add(pDO.location.accuracy);
            gpsAccuracy.dates.add(new Date(pDO.timestamp));
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Phone getPhoneById(@PathVariable Long id) {
        return phoneRepository.findOne(id);
    }

    public static class ServerRespose {
        public String status;

        ServerRespose(String status) {
            this.status = status;
        }
    }


}
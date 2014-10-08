package com.bg.phrobe;

import com.bg.phrobe.entities.Phone;
import com.bg.phrobe.entities.User;
import com.bg.phrobe.repository.PhoneRepository;
import com.bg.phrobe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestApp {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @RequestMapping("/test")
    String home() {
        Iterable<User> users = userRepository.findAll();

        return users.toString();
    }



    @RequestMapping(value = "/addphone", params = { "api", "nick" })
    @ResponseBody
    public String addPhone(@RequestParam("api") String api, @RequestParam("nick") String nick) {
        phoneRepository.save(new Phone(api, nick));
        return "Phone added with api:" + api + " nick: " + nick;
    }

    @RequestMapping(value = "/listphone")
    public String listPhone(){
        return phoneRepository.findAll().toString();
    }
}
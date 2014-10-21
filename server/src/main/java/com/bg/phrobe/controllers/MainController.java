package com.bg.phrobe.controllers;

import com.bg.phrobe.entities.Measurement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
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

    @RequestMapping("/serverInfo")
    @ResponseBody
    ServerInfo getServerAddress() {
        return new ServerInfo();
    }

    public static class ServerInfo {
        public String serverIp;

        ServerInfo() {
            this.serverIp = "not defined";
            InetAddress[] allMyIps = null;
            try {
                InetAddress localhost = InetAddress.getLocalHost();
                allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            if (allMyIps != null && allMyIps.length > 1) {
                this.serverIp = "";
                for(InetAddress ip : allMyIps){
                    this.serverIp += ip.getHostAddress() + "; ";
                }
            }
        }
    }

}
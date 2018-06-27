package tfb.springboot.raspberrypi.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tfb.springboot.raspberrypi.poc.services.LedOnOff;

/**
 * Created by Sandu
 * on 28.05.2018
 */

@RestController
@RequestMapping("/led")
public class LedController {

    @Autowired
    private LedOnOff service;


    @ResponseBody
    @GetMapping("/turnOn")
    public String turnOn() {

        System.out.println("TOGGLE LED  called");

        service.turnOn();

        return "turned on";
    }

    @ResponseBody
    @GetMapping("/turnOff")
    public String turnOff() {

        System.out.println("TOGGLE LED  called");

        service.turnOff();

        return "turned off";
    }
}

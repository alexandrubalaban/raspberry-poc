package tfb.springboot.raspberrypi.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tfb.springboot.raspberrypi.poc.services.LuminosityService;

/**
 * Created by Sandu
 * on 21.03.2018
 */
@RestController
@RequestMapping("/sensors")
public class LuminosityController {

    @Autowired
    private LuminosityService luminosityService;

    @ResponseBody
    @GetMapping("/luminosity")
    public Double getLuminosity() {
        return luminosityService.getLuminosity();
    }

}

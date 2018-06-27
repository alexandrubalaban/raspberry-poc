package tfb.springboot.raspberrypi.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tfb.springboot.raspberrypi.poc.services.ButtonService;

/**
 * Created by Sandu
 * on 04.06.2018
 */
//@RestController
public class ButtonController {

    @Autowired
    ButtonService buttonService;


}

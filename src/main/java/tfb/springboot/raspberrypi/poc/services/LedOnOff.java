package tfb.springboot.raspberrypi.poc.services;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Sandu
 * on 28.05.2018
 */

@Service
public class LedOnOff {

    private GpioPinDigitalOutput led;

    // create gpio controller
    private GpioController gpio;


    @PostConstruct
    public void init() {

        gpio = GpioFactory.getInstance();

        // creating the pin with parameter PinState.HIGH
        // will instantly power up the pin
        led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "PinLED", PinState.LOW);

    }

    public void turnOn() {
        led.high();
        led.toggle();
    }

    public void turnOff() {
        led.low();
        led.toggle();
    }
}

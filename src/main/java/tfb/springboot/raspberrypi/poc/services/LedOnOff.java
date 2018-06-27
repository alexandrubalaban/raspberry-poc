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

    private GpioController gpio;

    {
        System.out.println("In init +++++++++++++++++++++++++++++");

        gpio = GpioFactory.getInstance();

        led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "PinLED", PinState.LOW);
    }

    public void turnOn() {
        led.high();
        System.out.println("we put HIGH");
    }

    public void turnOff() {
        led.low();
        System.out.println("we put LOW");
    }
}

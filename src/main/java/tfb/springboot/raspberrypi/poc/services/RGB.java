package tfb.springboot.raspberrypi.poc.services;

import org.springframework.stereotype.Service;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

import javax.annotation.PostConstruct;


/**
 * Created by Sandu
 * on 27.06.2018
 */

public class RGB {



    final GpioController gpio = GpioFactory.getInstance();

    final GpioPinDigitalOutput greenPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "green", PinState.LOW);
    final GpioPinDigitalOutput bluePin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_23, "blue", PinState.LOW);
    final GpioPinDigitalOutput redPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_24, "red", PinState.LOW);

    /*
     * yellow  = R+G
     * cyan    = G+B
     * magenta = R+B
     * white   = R+G+B
     */



    public void initialization() throws InterruptedException {
        System.out.println("GPIO Control - pin 00, 01 & 02 ... started.");


        redPin.toggle();
        Thread.sleep(3000);

        greenPin.toggle();
        Thread.sleep(3000);

        bluePin.toggle();
        Thread.sleep(3000);


        redPin.low();
        greenPin.low();
        bluePin.low();

        gpio.shutdown();

    }
}

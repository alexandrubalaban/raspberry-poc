package tfb.springboot.raspberrypi.poc.services;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Sandu
 * on 04.06.2018
 */
@Service
public class ButtonService {
    static Boolean isLight = false;
    protected static final Logger logger = LoggerFactory.getLogger(LuminosityService.class);

    final GpioController gpio = GpioFactory.getInstance();


    final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_05,
            PinPullResistance.PULL_DOWN);

    // creating the pin with parameter PinState.HIGH
    // will instantly power up the pin
    final GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "PinLED", PinState.LOW);

    @PostConstruct
    public void init() {
        System.out.println("Init button listener");
        myButton.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println("buton apasat");
                if (event.getState().equals(PinState.HIGH)) {
                    if (isLight) {
                        isLight = false;
                        led.high();
                    } else {
                        isLight = true;
                        led.low();
                    }
                }

                led.toggle();


                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }

        });
    }


}

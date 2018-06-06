package tfb.springboot.raspberrypi.poc.services;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sandu
 * on 04.06.2018
 */
@Service
public class ButtonService {
    static Boolean isLight = false;
    protected static final Logger logger = LoggerFactory.getLogger(LuminosityService.class);

    final GpioController gpio = GpioFactory.getInstance();

    // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
    final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,
            PinPullResistance.PULL_DOWN);

    // creating the pin with parameter PinState.HIGH
    // will instantly power up the pin
    final GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "PinLED", PinState.LOW);

    public void pushButton() throws InterruptedException {
        myButton.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

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

                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }

        });

        // keep program running until user aborts (CTRL-C)
        while (true) {
            Thread.sleep(500);
        }
    }
}

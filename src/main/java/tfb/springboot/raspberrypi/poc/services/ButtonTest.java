package tfb.springboot.raspberrypi.poc.services; /**
 * Created by Sandu on 26.08.2017.
 */

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ButtonTest {

    static Boolean isLight = false;


    public static void main(String[] args) throws InterruptedException {


        System.out.println("<--Pi4J--> GPIO Trigger Example ... started.");

        final GpioController gpio = GpioFactory.getInstance();


        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,
                PinPullResistance.PULL_DOWN);


        final GpioPinDigitalOutput led = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "PinLED", PinState.LOW);

        System.out.println(" ... complete the GPIO #02 circuit and see the triggers take effect.");






        myButton.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

                if(event.getState().equals(PinState.HIGH)) {
                    if(isLight) {
                        isLight = false;
                        led.high();
                    }else {
                        isLight = true;
                        led.low();
                    }
                }

                led.toggle();


                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }

        });

        while (true) {
            Thread.sleep(500);
        }

    }
}


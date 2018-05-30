package tfb.springboot.raspberrypi.poc.services; /**
 * Created by Sandu on 26.08.2017.
 */

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class ButtonTest {

    static Boolean isLight = false;


    public static void main(String[] args) throws InterruptedException {

        //boolean isLight = false;

        System.out.println("<--Pi4J--> GPIO Trigger Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #02 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02,
                PinPullResistance.PULL_DOWN);

        // creating the pin with parameter PinState.HIGH
        // will instantly power up the pin
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

                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
            }

        });

        // keep program running until user aborts (CTRL-C)
        while (true) {
            Thread.sleep(500);
        }

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    }
}


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



        // setup gpio pins #04, #05, #06 as an output pins and make sure they are all LOW at startup
//        GpioPinDigitalOutput myLed[] = {
//                gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "LED #1", PinState.LOW),
//        };

        // create a gpio control trigger on the input pin ; when the input goes HIGH, also set gpio pin #04 to HIGH

//        myButton.addTrigger(new GpioSetStateTrigger(PinState.HIGH, myLed[0], PinState.HIGH));
//
//        // create a gpio control trigger on the input pin ; when the input goes LOW, also set gpio pin #04 to LOW
    //    myButton.addTrigger(new GpioSetStateTrigger(PinState.LOW, myLed[0], PinState.LOW));

        // create a gpio callback trigger on gpio pin#4; when #4 changes state, perform a callback
        // invocation on the user defined 'Callable' class instance
//        myButton.addTrigger(new GpioCallbackTrigger(new Callable<Void>() {
//            public Void call() throws Exception {
//                System.out.println(" --> GPIO TRIGGER CALLBACK RECEIVED ");
//                return null;
//            }
//        }));

//        myButton.addTrigger(new GpioCallbackTrigger(PinState.HIGH, new Callable<Void>() {
//            public Void call() throws Exception {
//                if(isLight ) {
//
//                    gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "PinLED", PinState.LOW);
//                    isLight = false;
//                }else {
//                    gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "PinLED", PinState.HIGH);
//                    isLight = true;
//                }
//                return null;
//            }
//        }));


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


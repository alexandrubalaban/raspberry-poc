package tfb.springboot.raspberrypi.poc.services;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public abstract class BaseSketch {

    protected static final Logger logger = LoggerFactory.getLogger("Sketch");
    protected final GpioController gpio;
    protected static Thread threadCheckInputStream;
    protected boolean isNotInterrupted = true;
    protected static final CountDownLatch countDownLatchEndSketch = new CountDownLatch(1);

    protected abstract void setup(String[] args);
    protected abstract void loop(String[] args) throws InterruptedException;   
    
    protected void loop() throws InterruptedException{
        loop(new String[0]);
    }

    public BaseSketch() {
        this(null);
    }
    
    public BaseSketch(GpioController gpio) {
        this.gpio = gpio;
    }

    protected void run(String[] args) throws InterruptedException {
        setup(args);
        startThreadToCheckInputStream();
        loop(args);
        tearDown();
    }

    private void startThreadToCheckInputStream() {
        CheckEnd checkend = new CheckEnd();
        threadCheckInputStream = new Thread(checkend);
        threadCheckInputStream.start();
    }

    protected void tearDown() {
        logger.debug("Shutting down gpio.");
        if(gpio != null){
            for(GpioPin pin : gpio.getProvisionedPins()){
                if(pin.getMode().equals(PinMode.DIGITAL_OUTPUT)){
                    pin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
                }
            }                
            gpio.shutdown();
        }
    }

    protected void delayMilliseconds(long miliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(miliseconds);
        } catch (InterruptedException ex) {
            logger.error("Sleep Milliseconds delay interrupted " + ex.getMessage());
        }
    }
    
    protected void delayMicrosendos(long microseconds){
        try {
            TimeUnit.MICROSECONDS.sleep(microseconds);
        } catch (InterruptedException ex) {
            logger.error("Sleep Microseconds delay interrupted " + ex.getMessage());
        }
    }

    protected void setSketchInterruption() {
        if (threadCheckInputStream != null && threadCheckInputStream.isAlive()) {
            threadCheckInputStream.interrupt();
        }
        isNotInterrupted = false;
    }

    private class CheckEnd implements Runnable {
        Scanner scanner = new Scanner(System.in);

        @SuppressWarnings("empty-statement")
        public void run() {
            while (!scanner.hasNextLine()) {};
            logger.debug("Sketch interrupted.");
            scanner.close();
            isNotInterrupted = false;
            countDownLatchEndSketch.countDown();
        }
    }
    
    
    protected static void wiringPiSetup(){
        if (Gpio.wiringPiSetup() == -1) {
            String msg = "==>> GPIO SETUP FAILED";
            logger.debug(msg);
            throw new RuntimeException(msg);
        }
    }

}

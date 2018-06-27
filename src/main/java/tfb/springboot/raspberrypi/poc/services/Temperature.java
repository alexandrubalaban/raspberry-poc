package tfb.springboot.raspberrypi.poc.services;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

/**
 * Created by Sandu
 * on 24.06.2018
 */
    public class Temperature extends ADC_Base {

        public Temperature(GpioController gpio) {
            super(gpio);
        }

        public static void main(String[] args) throws InterruptedException {
            Temperature sketch = new Temperature(GpioFactory.getInstance());
            sketch.run(args);
        }

        @Override
        protected void setup(String[] args) {
            super.setup(args);
            logger.debug("Analog temp sensor ready!");
        }

        @Override
        protected void loop(String[] args) {
            do {
                double temp = getTemperature(get_ADC_Result());
                logger.debug("Current temperature: "+temp);
                delayMilliseconds(1000);
            } while (isNotInterrupted);
        }


        private double getTemperature(double rawADC) {
            double KY_013Resistor = 10000; // For a 10k resistance thermistor.

            double b_constant = 3380.0;
            double t0 = 298; // 273 + 25, constant of the S.Hart equation.
            double celciusAdjustment = 273.15;
            double adc8BitPrecision = 256;

            double resisADC = ((adc8BitPrecision / rawADC)-1) * KY_013Resistor;
            double farenh = b_constant / Math.log(resisADC/(KY_013Resistor*Math.exp(-b_constant/t0)));
            return farenh - celciusAdjustment;
        }
    }


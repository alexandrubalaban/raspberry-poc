package tfb.springboot.raspberrypi.poc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Sandu
 * on 28.05.2018
 */
@Component
public class TempAndHumidityServiceMock implements TempAndHumidityService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    NumberFormat format = new DecimalFormat("#0.0");

    private static final int CHANNEL = 1;

    private static final int MIN_ADC_VALUE = 41;
    private static final int MAX_ADC_VALUE = 255;

    private static final double INTERVALS = 0.1869;

    @Autowired
    private ADCConnector adcConnector;

    @Override
    public String getTemperature() {

        short analogVal;
        double temperature;

        analogVal = adcConnector.get_ADC_Result(CHANNEL);
        logger.debug("ADC value: "+analogVal);
        System.out.println("ADC value: "+analogVal);

        temperature = (analogVal - MIN_ADC_VALUE ) * INTERVALS;
        logger.debug("" + temperature);
        System.out.println(temperature);
        return format.format(temperature);
    }
}

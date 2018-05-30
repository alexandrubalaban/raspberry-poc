package tfb.springboot.raspberrypi.poc.services;

import org.springframework.stereotype.Service;

/**
 * Created by Sandu
 * on 28.05.2018
 */

@Service
public interface TempAndHumidityService {

    String getTemperature();
}

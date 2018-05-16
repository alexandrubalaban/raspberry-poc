package tfb.springboot.raspberrypi.poc.services; /**
 * Created by Sandu on 26.08.2017.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    protected static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static LuminosityService luminosityService = new LuminosityService();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i ++) {
            logger.debug("Illuminance in LUX: " + luminosityService.getLuminosity() + "\n\n");
        }
    }
}
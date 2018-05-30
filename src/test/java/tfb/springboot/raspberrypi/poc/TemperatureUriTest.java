package tfb.springboot.raspberrypi.poc;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Sandu
 * on 28.05.2018
 */
public class TemperatureUriTest {
    @Test
    public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
            throws ClientProtocolException, IOException {

        // Given
        String name = "28";
        HttpUriRequest request = new HttpGet( "http://localhost:8080//sensors/luminosity");

    }
}

package tfb.springboot.raspberrypi.poc.Model;

import java.util.Date;

/**
 * Created by Sandu
 * on 23.06.2018
 */
public class Luminosity {

    Long Id;
    String luminosityLevel;
    Date date;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getLuminosityLevel() {
        return luminosityLevel;
    }

    public void setLuminosityLevel(String luminosityLevel) {
        this.luminosityLevel = luminosityLevel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

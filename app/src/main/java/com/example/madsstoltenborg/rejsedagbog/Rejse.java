package com.example.madsstoltenborg.rejsedagbog;

import java.util.Calendar;

/**
 * Created by Mads Stoltenborg on 23-04-2018.
 */

public class Rejse {
    private long id;
    private String destination;
    private Calendar tidsrum;
    private String beskrivelse;

    public Rejse(String destination, Calendar tidsrum) {
        this(-1, destination, tidsrum);
    }

    public Rejse(long id, String destination, Calendar tidsrum) {
        this.destination = destination;
        this.tidsrum = tidsrum;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Calendar getTidsrum() {
        return tidsrum;
    }

    public void setTidsrum(Calendar tidsrum) {
        this.tidsrum = tidsrum;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}

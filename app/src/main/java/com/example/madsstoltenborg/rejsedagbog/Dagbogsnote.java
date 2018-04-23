package com.example.madsstoltenborg.rejsedagbog;

import android.location.Location;

import java.net.URL;

/**
 * Created by Mads Stoltenborg on 23-04-2018.
 */

public class Dagbogsnote {
    private long id;
    private Rejse rejse;
    private String titel;
    private String beskrivelse;
    private Location lokation;
    private URL weblink;

    public Dagbogsnote(Rejse rejse, String titel, Location lokation) {
        this(-1, rejse, titel, lokation);
    }

    public Dagbogsnote(long id, Rejse rejse, String titel, Location lokation) {
        this.rejse = rejse;
        this.titel = titel;
        this.lokation = lokation;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Rejse getRejse() {
        return rejse;
    }

    public void setRejse(Rejse rejse) {
        this.rejse = rejse;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public Location getLokation() {
        return lokation;
    }

    public void setLokation(Location lokation) {
        this.lokation = lokation;
    }

    public URL getWeblink() {
        return weblink;
    }

    public void setWeblink(URL weblink) {
        this.weblink = weblink;
    }





}

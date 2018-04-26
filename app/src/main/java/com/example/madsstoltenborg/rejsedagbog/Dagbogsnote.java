package com.example.madsstoltenborg.rejsedagbog;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.net.URL;

/**
 * Created by Mads Stoltenborg on 23-04-2018.
 */

public class Dagbogsnote {
    private long id;
    private long rejse_id;
    private String titel;
    private String beskrivelse;
    private LatLng lokation;
    private String weblink;

    public Dagbogsnote(long rejse_id, String titel, String beskrivelse, LatLng lokation, String weblink) {
        this(-1, rejse_id, titel, beskrivelse, lokation, weblink);
    }

    public Dagbogsnote(long id, long rejse_id, String titel, String beskrivelse, LatLng lokation, String weblink) {
        this.rejse_id = rejse_id;
        this.beskrivelse = beskrivelse;
        this.titel = titel;
        this.lokation = lokation;
        this.weblink = weblink;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRejse_id() {
        return rejse_id;
    }

    public void setRejse_id(long rejse_id) {
        this.rejse_id = rejse_id;
    }

    public void setLokation(LatLng lokation) {
        this.lokation = lokation;
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

    public String getWeblink() {
        return weblink;
    }

    public void setWeblink(String weblink) {
        this.weblink = weblink;
    }





}

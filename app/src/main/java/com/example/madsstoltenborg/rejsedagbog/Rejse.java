package com.example.madsstoltenborg.rejsedagbog;

import java.util.Calendar;

/**
 * Created by Mads Stoltenborg on 23-04-2018.
 */

public class Rejse {
    private long id;
    private String rejseNavn;
    private Calendar rejseStart;
    private Calendar rejseSlut;
    private String beskrivelse;

    public Rejse(String destination, Calendar rejseStart, Calendar rejseSlut) {
        this(-1, destination, rejseStart, rejseSlut);
    }

    public Rejse(long id, String rejseNavn, Calendar rejseStart, Calendar rejseSlut) {
        this.rejseNavn = rejseNavn;
        this.rejseStart = rejseStart;
        this.rejseSlut = rejseSlut;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRejseNavn() {
        return rejseNavn;
    }

    public void setRejseNavn(String rejseNavn) {
        this.rejseNavn = rejseNavn;
    }

    public Calendar getRejseStart() {
        return rejseStart;
    }

    public void setRejseStart(Calendar rejseStart) {
        this.rejseStart = rejseStart;
    }

    public Calendar getRejseSlut() {
        return rejseSlut;
    }

    public void setRejseSlut(Calendar rejseSlut) {
        this.rejseSlut = rejseSlut;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }
}

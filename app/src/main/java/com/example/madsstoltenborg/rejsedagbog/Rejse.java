package com.example.madsstoltenborg.rejsedagbog;

import java.util.Calendar;

/**
 * Created by Mads Stoltenborg on 23-04-2018.
 */

public class Rejse {
    private long id;
    private String rejseNavn;
    private String rejseStart;
    private String rejseSlut;
    private String beskrivelse;

    public Rejse(String rejseNavn, String rejseStart, String rejseSlut, String beskrivelse) {
        this(-1, rejseNavn, rejseStart, rejseSlut, beskrivelse);
    }

    public Rejse(long id, String rejseNavn, String rejseStart, String rejseSlut, String beskrivelse) {
        this.rejseNavn = rejseNavn;
        this.rejseStart = rejseStart;
        this.rejseSlut = rejseSlut;
        this.beskrivelse = beskrivelse;
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

    public String getRejseStart() {
        return rejseStart;
    }

    public void setRejseStart(String rejseStart) {
        this.rejseStart = rejseStart;
    }

    public String getRejseSlut() {
        return rejseSlut;
    }

    public void setRejseSlut(String rejseSlut) {
        this.rejseSlut = rejseSlut;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    @Override
    public String toString() {
        return rejseNavn;
    }
}

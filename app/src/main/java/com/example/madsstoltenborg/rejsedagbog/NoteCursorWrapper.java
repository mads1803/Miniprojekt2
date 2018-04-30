package com.example.madsstoltenborg.rejsedagbog;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NoteCursorWrapper extends CursorWrapper {

    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dagbogsnote getDagbogsNote(){
        long id = getLong(getColumnIndex("_id"));
        String titel = getString(getColumnIndex("TITEL"));
        long rejseId = getLong(getColumnIndex("REJSE_ID"));
        String beskrivelse = getString(getColumnIndex("BESKRIVELSE"));
        String lokationStr = getString(getColumnIndex("LOKATION"));

        String weblinkStr = getString(getColumnIndex("WEBLINK"));
        String datoStr = getString(getColumnIndex("DATO"));


        String[] latlong = lokationStr.split(",");
        double lat = Double.parseDouble(latlong[0]);
        double longi = Double.parseDouble(latlong[1]);

        String[] web = weblinkStr.split("www");
        String weblink = "http://www" + web[1];
        

        //TODO Lav longitude og latitude til en lokation
        LatLng lokation = new LatLng(lat, longi);
        return new Dagbogsnote(id, rejseId, titel, beskrivelse, lokation ,weblink, datoStr);

    }


}

package com.example.madsstoltenborg.rejsedagbog;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.google.android.gms.maps.model.LatLng;

public class NoteCursorWrapper extends CursorWrapper {

    public NoteCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dagbogsnote getDagbogsNote(){
long id = getLong(getColumnIndex("_id"));
String titel = getString(getColumnIndex("TITEL"));
long rejseId = getLong(getColumnIndex("REJSE_ID"));
String beskrivelse = getString(getColumnIndex("BESKRIVELSE"));
String longitude = getString(getColumnIndex("LONGITUDE"));
String latitude = getString(getColumnIndex("LATITUDE"));
String weblink = getString(getColumnIndex("WEBLINK"));

double lat = Double.parseDouble(longitude);
double longi = Double.parseDouble(latitude);
//TODO Lav longitude og latitude til en lokation
        LatLng lokation = new LatLng(lat, longi);
return new Dagbogsnote(id, rejseId, titel, beskrivelse, lokation ,weblink);

    }


}

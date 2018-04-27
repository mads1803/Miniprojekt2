package com.example.madsstoltenborg.rejsedagbog;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RejseCursorWrapper extends CursorWrapper {

    public RejseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Rejse getRejse(){
        long id = getLong(getColumnIndex("_id"));
        String rejseNavn = getString(getColumnIndex("REJSENAVN"));
        String rejseStartText = getString(getColumnIndex("TIDSRUMFRA"));
        String rejseSlutText = getString(getColumnIndex("TIDSRUMTIL"));
        String beskrivelse = getString(getColumnIndex("BESKRIVELSE"));

       Calendar calStart = Calendar.getInstance();
       Calendar calSlut = Calendar.getInstance();

        Date startRejse;
        Date slutRejse;
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
        startRejse = df.parse(rejseStartText);
        calStart.setTime(startRejse);
        slutRejse = df.parse(rejseSlutText);
        calSlut.setTime(slutRejse);

        } catch (ParseException e){
            e.printStackTrace();
        }

     return new Rejse(id, rejseNavn, calStart, calSlut, beskrivelse);
    }
}

package com.example.madsstoltenborg.rejsedagbog;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Calendar;

public class RejseCursorWrapper extends CursorWrapper {

    public RejseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Rejse getRejse(){
        long id = getLong(getColumnIndex("_id"));
        String rejseNavn = getString(getColumnIndex("REJSENAVN"));
        long rejseStartMillis = getLong(getColumnIndex("TIDSRUMFRA"));
        long rejseSlutMillis = getLong(getColumnIndex("TIDSRUMTIL"));
        String beskrivelse = getString(getColumnIndex("BESKRIVELSE"));

        Calendar rejseStart = Calendar.getInstance();
        rejseStart.setTimeInMillis(rejseStartMillis);

        Calendar rejseSlut = Calendar.getInstance();
        rejseSlut.setTimeInMillis(rejseSlutMillis);

     return new Rejse(id, rejseNavn, rejseStart, rejseSlut, beskrivelse);
    }
}

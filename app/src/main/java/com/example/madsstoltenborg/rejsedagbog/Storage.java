package com.example.madsstoltenborg.rejsedagbog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

/**
 * Created by Mads Stoltenborg on 23-04-2018.
 */


public class Storage {

    private static Storage storage;
    private static SQLiteDBHelper rejseDatabaseHelper = SQLiteDBHelper.getInstance();

    //private static SQLiteDatabase shoppingDatabaseHelper = SQLiteDatabase.getInstance();

    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
            storage.addDummyData();
        }
        return storage;
    }

    //DUMMY
public void addDummyData(){

        if (getRejse().getCount() == 0){

            Calendar rejsStart = Calendar.getInstance();
          //  rejsStart.setTimeInMillis(1524743899);
            Calendar rejsSlut = Calendar.getInstance();
         //   rejsSlut.setTimeInMillis(1524749999);
            rejsStart.set(2014, 10, 30);
            rejsSlut.set(2015, 01, 01);
            insertRejse("Himalaya", "Løbetur", rejsStart,rejsSlut);

            rejsStart.set(2016, 01, 25);
            rejsSlut.set(2016, 01, 30);
            insertRejse("Bornholm", "fisketur", rejsStart, rejsSlut);

         

        }
}


    //TODO CRUD REJSE

    public static void insertRejse(String rejseNavn, String beskrivelse, Calendar rejseStart, Calendar rejseSlut){
//        long rejseStartinMillis =  rejseStart.getTimeInMillis();
  //      long rejseSlutinMillis =  rejseSlut.getTimeInMillis();
        String rejseStartinText = rejseStart.toString();
        String rejseSlutinText = rejseSlut.toString();

        SQLiteDatabase db = rejseDatabaseHelper.getWritableDatabase();

        ContentValues rejseValues = new ContentValues();
        rejseValues.put("REJSENAVN", rejseNavn);
        rejseValues.put("TIDSRUMFRA", rejseStartinText);
        rejseValues.put("TIDSRUMTIL", rejseSlutinText);
        rejseValues.put("BESKRIVELSE", beskrivelse);
        db.insert("REJSE", null, rejseValues);
    }


    // TODO CRUD DAGBOGSNOTE
    public static void insertDagbogsNote (String titel, int rejse_id, String beskrivelse, LatLng lokation, String weblink, Calendar dato){
       String lokationStr = lokation.toString();
        String datoStr = dato.toString();

        SQLiteDatabase db = rejseDatabaseHelper.getWritableDatabase();
        ContentValues dagbogsValues = new ContentValues();
        dagbogsValues.put("TITEL", titel);
        dagbogsValues.put("REJSE_ID", rejse_id);
        dagbogsValues.put("BESKRIVELSE", beskrivelse);
        dagbogsValues.put("LOKATION", lokationStr);
        dagbogsValues.put("WEBLINK", weblink);
        dagbogsValues.put("DATO", datoStr);
        db.insert("NOTE", null, dagbogsValues);

    }

    public RejseCursorWrapper getRejse(){
        SQLiteDatabase db = rejseDatabaseHelper.getReadableDatabase();
        Cursor cursor =  db.query("REJSE",
                new String[]{"_id", "REJSENAVN", "TIDSRUMFRA", "TIDSRUMTIL", "BESKRIVELSE"},
                null, null, null, null, null, null);
        return new RejseCursorWrapper(cursor);
    }

    //TODO note fra id
    public NoteCursorWrapper getDagbogsNote(long rejse_id){
        SQLiteDatabase db = rejseDatabaseHelper.getReadableDatabase();
        Cursor cursor =  db.query("NOTE",
                new String[]{"_id", "TITEL", "REJSE_ID", "BESKRIVELSE", "LOKATION", "WEBLINK", "DATO"},
                "REJSE_ID = " + rejse_id, null, null, null, null, null);
        return new NoteCursorWrapper(cursor);
    }

}


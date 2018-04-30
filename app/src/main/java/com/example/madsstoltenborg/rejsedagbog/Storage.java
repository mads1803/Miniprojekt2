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


            insertRejse("Himalaya", "Løbetur", "2014-10-13","02-11-2016");
            insertRejse("Bornholm", "fisketur", "2014-10-13","22-10-2012");
            insertRejse("USA", "fisketur", "2014-10-13","22-10-2012");
            insertRejse("England", "fisketur", "2014-10-13","22-10-2012");


            LatLng lokation = new LatLng(10, 56);
insertDagbogsNote("Hej jeg er på bornholm",2, "Fisker med pølse", lokation, "www.bcc.dk", "02-12-2016");
insertDagbogsNote("Hej jeg er på Himalaya",1, "Løber", lokation, "www.hima.dk", "02-12-2016");


        }
}


    //TODO CRUD REJSE

    public static void insertRejse(String rejseNavn, String beskrivelse, String rejseStart, String rejseSlut){
//        long rejseStartinMillis =  rejseStart.getTimeInMillis();
  //      long rejseSlutinMillis =  rejseSlut.getTimeInMillis();
      // String rejseStartinText = rejseStart.toString();
       // String rejseSlutinText = rejseSlut.toString();

        SQLiteDatabase db = rejseDatabaseHelper.getWritableDatabase();

        ContentValues rejseValues = new ContentValues();
        rejseValues.put("REJSENAVN", rejseNavn);
        rejseValues.put("TIDSRUMFRA", rejseStart);
        rejseValues.put("TIDSRUMTIL", rejseSlut);
        rejseValues.put("BESKRIVELSE", beskrivelse);
        db.insert("REJSE", null, rejseValues);
    }

    public static void updateRejse(long _id, String rejseNavn, String beskrivelse, String rejseStart, String rejseSlut){
//        long rejseStartinMillis =  rejseStart.getTimeInMillis();
        //      long rejseSlutinMillis =  rejseSlut.getTimeInMillis();
        // String rejseStartinText = rejseStart.toString();
        // String rejseSlutinText = rejseSlut.toString();

        SQLiteDatabase db = rejseDatabaseHelper.getWritableDatabase();

        ContentValues rejseValues = new ContentValues();
        rejseValues.put("REJSENAVN", rejseNavn);
        rejseValues.put("TIDSRUMFRA", rejseStart);
        rejseValues.put("TIDSRUMTIL", rejseSlut);
        rejseValues.put("BESKRIVELSE", beskrivelse);
        db.update("REJSE", rejseValues, "_id=" + _id, null);
    db.close();
    }


    // TODO CRUD DAGBOGSNOTE
    public static void insertDagbogsNote (String titel, int rejse_id, String beskrivelse, LatLng lokation, String weblink, String dato){
       //String lokationStr = lokation.toString();
        //String datoStr = dato.toString();

        double lat = lokation.latitude;
        double longi = lokation.longitude;

        String lokationStr = ""+lat +","+longi;

        SQLiteDatabase db = rejseDatabaseHelper.getWritableDatabase();
        ContentValues dagbogsValues = new ContentValues();
        dagbogsValues.put("TITEL", titel);
        dagbogsValues.put("REJSE_ID", rejse_id);
        dagbogsValues.put("BESKRIVELSE", beskrivelse);
        dagbogsValues.put("LOKATION", lokationStr);
        dagbogsValues.put("WEBLINK", weblink);
        dagbogsValues.put("DATO", dato);
        db.insert("NOTE", null, dagbogsValues);
    }

    public static void updateDagbogsNote (long note_id, String titel, int rejse_id, String beskrivelse, LatLng lokation, String weblink, String dato){

        double lat = lokation.latitude;
        double longi = lokation.longitude;

        String lokationStr = ""+lat +","+longi;

        SQLiteDatabase db = rejseDatabaseHelper.getWritableDatabase();
        ContentValues dagbogsValues = new ContentValues();
        dagbogsValues.put("TITEL", titel);
        dagbogsValues.put("REJSE_ID", rejse_id);
        dagbogsValues.put("BESKRIVELSE", beskrivelse);
        dagbogsValues.put("LOKATION", lokationStr);
        dagbogsValues.put("WEBLINK", weblink);
        dagbogsValues.put("DATO", dato);
        db.update("NOTE", dagbogsValues, "_id=" + note_id, null);
        db.close();
    }


    // TODO getters
    public RejseCursorWrapper getRejse(){
        SQLiteDatabase db = rejseDatabaseHelper.getReadableDatabase();
        Cursor cursor =  db.query("REJSE",
                new String[]{"_id", "REJSENAVN", "TIDSRUMFRA", "TIDSRUMTIL", "BESKRIVELSE"},
                null, null, null, null, null, null);
        return new RejseCursorWrapper(cursor);
    }

    public NoteCursorWrapper getDagbogsNote(long rejse_id){
        SQLiteDatabase db = rejseDatabaseHelper.getReadableDatabase();
        Cursor cursor =  db.query("NOTE",
                new String[]{"_id", "TITEL", "REJSE_ID", "BESKRIVELSE", "LOKATION", "WEBLINK", "DATO"},
                "REJSE_ID = " + rejse_id, null, null, null, null, null);
        return new NoteCursorWrapper(cursor);
    }

    public NoteCursorWrapper getNote(long note_id){
        SQLiteDatabase db = rejseDatabaseHelper.getReadableDatabase();
        Cursor cursor =  db.query("NOTE",
                new String[]{"_id", "TITEL", "REJSE_ID", "BESKRIVELSE", "LOKATION", "WEBLINK", "DATO"},
                "_id = " + note_id, null, null, null, null, null);
        return new NoteCursorWrapper(cursor);
    }
}


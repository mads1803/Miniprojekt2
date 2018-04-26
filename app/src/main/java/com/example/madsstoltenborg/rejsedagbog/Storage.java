package com.example.madsstoltenborg.rejsedagbog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
           // storage.addDummyData();
        }
        return storage;
    }

    //DUMMY
public void addDummyData(){

        if (getRejse().getCount() == 0){

            Calendar rejsStart = Calendar.getInstance();
            rejsStart.setTimeInMillis(1524743899);
            Calendar rejsSlut = Calendar.getInstance();
            rejsSlut.setTimeInMillis(1524749999);

            insertRejse("Himalaya", "Løbetur", rejsStart,rejsSlut);
        }
}


    //TODO CRUD REJSE

    public static void insertRejse(String rejseNavn, String beskrivelse, Calendar rejseStart, Calendar rejseSlut ){
        long rejseStartinMillis =  rejseStart.getTimeInMillis();
        long rejseSlutinMillis =  rejseSlut.getTimeInMillis();
        SQLiteDatabase db = rejseDatabaseHelper.getWritableDatabase();

        ContentValues rejseValues = new ContentValues();
        rejseValues.put("REJSENAVN", rejseNavn);
        rejseValues.put("TIDSRUMFRA", rejseStartinMillis);
        rejseValues.put("TIDSRUMTIL", rejseSlutinMillis);
        rejseValues.put("BESKRIVELSE", beskrivelse);
        db.insert("REJSE", null, rejseValues);
    }


    public RejseCursorWrapper getRejse(){
        SQLiteDatabase db = rejseDatabaseHelper.getReadableDatabase();
        Cursor cursor =  db.query("REJSE",
                new String[]{"_id", "REJSENAVN", "TIDSRUMFRA", "TIDSRUMTIL", "BESKRIVELSE"},
                null, null, null, null, null, null);
        return new RejseCursorWrapper(cursor);
    }

    // TODO CRUD DAGBOGSNOTE
    public static void insertDagbogsNote (String titel, int rejse_id, String beskrivelse, String longitude, String latitude, String weblink ){
        SQLiteDatabase db = rejseDatabaseHelper.getWritableDatabase();
        ContentValues dagbogsValues = new ContentValues();
        dagbogsValues.put("TITEL", titel);
        dagbogsValues.put("REJSE_ID", rejse_id);
        dagbogsValues.put("BESKRIVELSE", beskrivelse);
        dagbogsValues.put("LONGITUDE", longitude);
        dagbogsValues.put("LATITUDE", latitude);
        dagbogsValues.put("WEBLINK", weblink);
db.insert("NOTE", null, dagbogsValues);

    }
    

}


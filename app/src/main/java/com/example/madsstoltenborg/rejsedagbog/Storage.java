package com.example.madsstoltenborg.rejsedagbog;

import android.content.ContentValues;
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
    
    // TODO CRUD DAGBOGSNOTE

    //

}


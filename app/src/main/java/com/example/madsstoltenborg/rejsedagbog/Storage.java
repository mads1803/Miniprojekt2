package com.example.madsstoltenborg.rejsedagbog;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Mads Stoltenborg on 23-04-2018.
 */

public class Storage {
    private static Storage storage;
    //private static SQLiteDatabase shoppingDatabaseHelper = SQLiteDatabase.getInstance();

    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
           // storage.addDummyData();
        }
        return storage;
    }


}


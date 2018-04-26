package com.example.madsstoltenborg.rejsedagbog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mads Stoltenborg on 23-04-2018.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "Shopping";
    private static final int DB_VERSION = 2;
    private static Context applicationContext;
    private static SQLiteDBHelper RejseDBhelper;

    public static void setApplicationContext(Context context){
        applicationContext = context.getApplicationContext();
    }

    public SQLiteDBHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);}

    public static SQLiteDBHelper getInstance(){
        if (RejseDBhelper == null) {
            RejseDBhelper = new SQLiteDBHelper(applicationContext);
        }
        return RejseDBhelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }


    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <= 1) {
            db.execSQL("CREATE TABLE REJSE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "REJSENAVN TEXT, "
                    + "TIDSRUMFRA LONG, "
                    + "TIDSRUMTIL LONG, "
                    + "BESKRIVELSE TEXT);");
//TODO evt ændrer tidsrum til strings  - da milliseconds var for lang en integer til testdata
            db.execSQL("CREATE TABLE NOTE ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "TITEL TEXT, "
                    + "REJSE_ID INTEGER REFERENCES REJSE(_id) ON DELETE CASCADE, "
                    + "BESKRIVELSE TEXT, "
                    + "LONGITUDE TEXT, "
                    + "LATITUDE TEXT, "
                    + "WEBLINK TEXT);");



//            //TODO: View til shopproducts where productid=shopid
//            //a = shopproducts, b = products, c = shops
//            db.execSQL("CREATE VIEW SHOP_PRODUCTS_VIEW AS " +
//                    "SELECT " +
//                    "A._id AS _id, A.SHOP_ID AS SHOP_ID, A.PRODUCT_ID AS PRODUCT_ID, B.NAME AS PRODUCTNAME, B.VOLUME AS VOLUME, A.PRICE AS PRICE, C.NAME AS SHOPNAME " +
//                    "FROM SHOP_PRODUCTS A, PRODUCT B, SHOP C " +
//                    "WHERE A.PRODUCT_ID = B._id AND A.SHOP_ID = C._id;");


        }

    }
}

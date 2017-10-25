package com.f.minmadairports;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by ferdinand on 25-10-2017.
 */

public class AirportsDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "airports.sqlite";
    private static final int DATABASE_VERSION = 1;


    public AirportsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getAllAirports() {
        SQLiteDatabase db = getReadableDatabase();

        //String query = "SELECT icao, name FROM airports WHERE iso_country = \"NL\"";
        String query = "SELECT rowid _id, * FROM airports";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        db.close();
        return c;
    }
}

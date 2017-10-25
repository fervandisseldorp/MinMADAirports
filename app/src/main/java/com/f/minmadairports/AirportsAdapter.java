package com.f.minmadairports;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by ferdinand on 25-10-2017.
 */

public class AirportsAdapter extends CursorAdapter {

    public AirportsAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.listview_row, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //ViewHolder holder = new ViewHolder();
        TextView nameTV = (TextView) view.findViewById(R.id.airportNameTV);
        TextView icaoTV = (TextView) view.findViewById(R.id.airportIcaoTV);

//        String name = cursor.getString(cursor.getColumnIndex("name"));
//        String icao = cursor.getString(cursor.getColumnIndex("icao"));

        nameTV.setText("Airport: " + cursor.getString(cursor.getColumnIndex("name")) );
        icaoTV.setText("Icao: + " + cursor.getString(cursor.getColumnIndex("icao")));

//        view.setTag(holder);

    }


}

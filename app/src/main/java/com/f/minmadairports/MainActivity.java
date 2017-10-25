package com.f.minmadairports;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView airportsListView;
    ArrayList airportsList;
    //ArrayAdapter<Airport> adapter;
    CursorAdapter adapter;
    AirportsDatabase database;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airportsListView = (ListView) findViewById(R.id.airportsListView);
        airportsListView.setOnItemClickListener(this);

         database = new AirportsDatabase(this);
        cursor = database.getAllAirports();
        
        adapter = new AirportsAdapter(getApplicationContext(), cursor);
        airportsListView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("Clicked on item");

        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        cursor.moveToPosition( position );

        String icao = cursor.getString(cursor.getColumnIndex("icao"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        double lattitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
        double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
        String elevation = cursor.getString(cursor.getColumnIndex("elevation"));
        String isoCountry = cursor.getString(cursor.getColumnIndex("iso_country"));
        String city = cursor.getString(cursor.getColumnIndex("municipality"));
        Airport selectedAirport = new Airport(icao, name, lattitude, longitude, elevation, isoCountry, city);

        intent.putExtra("AIRPORT", selectedAirport);
        startActivity(intent);
    }


}

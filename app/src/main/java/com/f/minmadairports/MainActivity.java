package com.f.minmadairports;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.CursorTreeAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ExpandableListView.OnChildClickListener {
    ExpandableListView expAirportsListView;
    AirportsExpAdapter adapter;
    AirportsDatabase database;
    Cursor cursor;
    private List<String> headers;
    private HashMap<String, List<Airport>> airportsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airportslist);

        expAirportsListView = (ExpandableListView) findViewById(R.id.exp_airportsListView);
        expAirportsListView.setOnChildClickListener(this);

        database = new AirportsDatabase(this);
        cursor = database.getAllAirports();

        loadData();

        adapter = new AirportsExpAdapter(getApplicationContext(), headers, airportsList);
        expAirportsListView.setAdapter( adapter );

    }

    public void loadData(){
        headers = new ArrayList<String>();
        airportsList = new HashMap<String, List<Airport>>();

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
            String icao = cursor.getString(cursor.getColumnIndex("icao"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            double lattitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
            double longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
            String elevation = cursor.getString(cursor.getColumnIndex("elevation"));
            String isoCountry = cursor.getString(cursor.getColumnIndex("iso_country"));
            String city = cursor.getString(cursor.getColumnIndex("municipality"));
            Airport airport = new Airport(icao, name, lattitude, longitude, elevation, isoCountry, city);

            // if country has no header yet
            if( !headers.contains( isoCountry) ){
                System.out.println("Groups does not have " + isoCountry + " yet. Adding it now");
                headers.add( isoCountry );

                ArrayList<Airport> children = new ArrayList<Airport>();
                children.add( airport );

                airportsList.put( isoCountry, children);
            }
            // Group already exists
            else {
                // find the group with the correct country
                for(Map.Entry<String, List<Airport>> group : airportsList.entrySet()){
                    String key = group.getKey();
                    if(key.equals(isoCountry) ){
                        ArrayList<Airport> children = (ArrayList<Airport>) group.getValue();
                        children.add( airport );
                        group.setValue( children );
                    }
                }
            }
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        //Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
        String key = headers.get( groupPosition );

        ArrayList<Airport> selectedHeader = (ArrayList<Airport>) airportsList.get( key );
        Airport selectedAirport = selectedHeader.get( childPosition );

        intent.putExtra("AIRPORT", selectedAirport);
        startActivity(intent);

        return false;
    }
}

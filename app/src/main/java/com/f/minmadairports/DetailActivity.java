package com.f.minmadairports;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by ferdinand on 25-10-2017.
 */

public class DetailActivity extends FragmentActivity {
    TextView nameTV;
    TextView icaoTV;
    TextView distanceTV;
    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airport_details);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        //mapFragment.getMapAsync(this);

        Airport selectedAirport = (Airport) getIntent().getSerializableExtra("AIRPORT");

        nameTV = (TextView) findViewById(R.id.detailNameTV);
        icaoTV = (TextView) findViewById(R.id.DetailsIcaoTV);

        nameTV.setText( selectedAirport.getName() );
        icaoTV.setText(selectedAirport.getIcao() );



    }



}

package com.f.minmadairports;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by ferdinand on 25-10-2017.
 */

public class DetailActivity extends FragmentActivity implements OnMapReadyCallback {
    TextView nameTV;
    TextView icaoTV;
    TextView distanceTV;
    private GoogleMap mMap;
    private Airport selectedAirport;
    private double distance = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airport_details);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.googleMap);

        selectedAirport = (Airport) getIntent().getSerializableExtra("AIRPORT");

        nameTV = (TextView) findViewById(R.id.detailNameTV);
        icaoTV = (TextView) findViewById(R.id.DetailsIcaoTV);
        distanceTV = (TextView) findViewById(R.id.distanceTV);

        nameTV.setText( "Airport: " + selectedAirport.getName() );
        icaoTV.setText("ICAO: " + selectedAirport.getIcao() );


        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng schiphol = new LatLng(52.3105386, 4.7682744);

        double endLatitude = selectedAirport.getLattitude();
        double endLongitude = selectedAirport.getLongitude();

        LatLng destination = new LatLng(endLatitude, endLongitude);

        Location startLocation = new Location("");
        startLocation.setLatitude(52.3105386);
        startLocation.setLongitude(4.7682744);
        Location endLocation = new Location("");
        endLocation.setLatitude( endLatitude);
        endLocation.setLongitude( endLongitude);
        distance = startLocation.distanceTo(endLocation);
        distanceTV.setText("Distance: " + distance + " meters" );

        mMap.addMarker(new MarkerOptions().position(schiphol).title("Schiphol Airport"));
        mMap.addMarker(new MarkerOptions().position(destination).title(selectedAirport.getName()));

        PolylineOptions line=
                new PolylineOptions()
                        .add(schiphol, destination)
                        .geodesic(true)
                        .width(4).color(Color.RED);

        mMap.addPolyline(line);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(destination));
    }
}

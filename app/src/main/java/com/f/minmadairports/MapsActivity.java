package com.f.minmadairports;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Airport selectedAirport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        selectedAirport = (Airport) getIntent().getSerializableExtra("AIRPORT");
        System.out.println("selected destination " + selectedAirport.getLattitude() + " " + selectedAirport.getLongitude() );

        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng schiphol = new LatLng(52.3105386, 4.7682744);

        double endLatitude = selectedAirport.getLattitude();
        double endLongitude = selectedAirport.getLongitude();

        LatLng destination = new LatLng(endLatitude, endLongitude);

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

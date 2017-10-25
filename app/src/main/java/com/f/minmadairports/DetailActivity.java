package com.f.minmadairports;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by ferdinand on 25-10-2017.
 */

public class DetailActivity extends AppCompatActivity {
    TextView nameTV;
    TextView icaoTV;
    TextView distanceTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airport_details);

        Airport selectedAirport = (Airport) getIntent().getSerializableExtra("AIRPORT");

        nameTV = (TextView) findViewById(R.id.detailNameTV);
        icaoTV = (TextView) findViewById(R.id.DetailsIcaoTV);

        nameTV.setText( selectedAirport.getName() );
        icaoTV.setText(selectedAirport.getIcao() );

    }
}

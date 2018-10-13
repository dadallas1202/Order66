package edu.mgrace31gatech.donationtracker.app.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.mgrace31gatech.donationtracker.R;

public class LocationDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Log.d("MYAPP", "Location Detail Activity ");
            Bundle arguments = new Bundle();
            arguments.putInt(LocationDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(LocationDetailFragment.ARG_ITEM_ID, 1000));
            LocationDetailFragment fragment = new LocationDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.location_detail_container, fragment)
                    .commit();
        }
    }
}
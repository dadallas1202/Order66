package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.InventoryModel;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

public class DonationActivity extends Activity {

    private EditText Name;
    private EditText shortDescription;
    private EditText fullDescription;
    private EditText Value;
    private Spinner Category;
    private Button addButton;

    private Donation _donation;
    private InventoryModel _location = InventoryModel.INSTANCE;

    //private List<String> mDonationNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        /*
          Grab the dialog widgets so we can get info for later
         */
        Name = (EditText)findViewById(R.id.nameDonationBox);
        shortDescription = (EditText)findViewById(R.id.shortDescriptionBox);
        fullDescription = (EditText)findViewById(R.id.fullDescriptionBox);
        Value = (EditText)findViewById(R.id.valueBox);
        Category = (Spinner)findViewById(R.id.categorySpinner);
        addButton = (Button)findViewById(R.id.addButton);

        /*
          Set up the adapter to display the allowable categories in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Donation.categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalTime localtime = LocalTime.now();
                LocalDate localdate = LocalDate.now();
                LocationsModel model = LocationsModel.INSTANCE;
                List<Location> myLocations = model.getLocations();
                double value = Double.parseDouble(Value.getText().toString());
                _donation = new Donation(Name.getText().toString(), shortDescription.getText().toString(), fullDescription.getText().toString()
                        , value, Category.getSelectedItem().toString(), localtime, localdate);
//                Intent myIntent = getIntent();
//                if (myIntent == null) {
//                    Log.d("MYAPP", "Intent is Null");
//                } else {
//                    Log.d("MYAPP", "Got intent ");
//                }
                int locationID = getIntent().getIntExtra(LocationDetailFragment.ARG_ITEM_ID, 1000);
                for (Location l : myLocations) {
                    if (l.getKey() == locationID) {
                        _location = l;
                        Log.d("MYAPP", l.getName());

                    }
                }
//                _location = (Location) getIntent().getParcelableExtra(LocationDetailFragment.ARG_ITEM_ID);
                if (_location == null) {
                    Log.d("MYAPP", "Location is Null");
                }
                _location.addDonation(_donation);
                Intent intent = new Intent(DonationActivity.this, InventoryActivity.class);
                intent.putExtra(LocationDetailFragment.ARG_ITEM_ID, getIntent().getIntExtra(LocationDetailFragment.ARG_ITEM_ID, 1000));
                startActivity(intent);
            }
        });
    }

}

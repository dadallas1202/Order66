package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.DonationModel;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

public class AddDonationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    /* ************************
    Widgets we will need for binding and getting information
       */
    private EditText Name;
    private EditText shortDescription;
    private EditText fullDescription;
    private EditText Value;
    private Spinner Category;

    /*
        Keeping track of spinner changes. Not really used right now, probably don't need this.
     */
    private String _category = "NA";

    /* ***********************
       Data for donation being added.
     */
    private Donation _donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        /*
          Grab the dialog widgets so we can get info for later
         */
        Name = (EditText)findViewById(R.id.nameDonationBox);
        shortDescription = (EditText)findViewById(R.id.shortDescriptionBox);
        fullDescription = (EditText)findViewById(R.id.fullDescriptionBox);
        Value = (EditText)findViewById(R.id.valueBox);
        Category = (Spinner)findViewById(R.id.categorySpinner);

        /*
          Set up the adapter to display the allowable categories in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Donation.categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category.setAdapter(adapter);

        _donation = new Donation();

        //Name.setText(_donation.getName());
    }

    /**
     * Button handler for the add new donation button
     * @param view the button
     */
    public void onAddPressed(View view) {
        Log.d("Edit", "Add Donation");
        DonationModel model = DonationModel.getInstance();
        LocationsModel model2 = LocationsModel.getInstance();
        double value = Double.parseDouble(Value.getText().toString());
        _donation.setName(Name.getText().toString());
        _donation.setShortDescription(shortDescription.getText().toString());
        _donation.setLongDescription(fullDescription.getText().toString());
        _donation.setValue(value);
        _donation.setCategory(Category.getSelectedItem().toString());
        _donation.setTime(LocalTime.now());
        _donation.setDate(LocalDate.now());
        _donation.setLocation(model2.getCurrentLocation());

        Log.d("Edit", "Got new donation data: " + _donation);
        model.addDonation(_donation);

        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _category = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { _category = "NA"; }
}

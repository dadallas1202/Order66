package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.DonationModel;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;
import edu.mgrace31gatech.donationtracker.app.model.RegisteredUser;

/**
 * Activity to control the process of adding a new donation to the app.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace, Alayna Panlilio, Julia Tang
 *
 */
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
        Location location = model2.getCurrentLocation();
//        location.setInventory(getList(location.getName()) == null ? new ArrayList<Donation>() : getList(location.getName()));
        double value = Double.parseDouble(Value.getText().toString());
        _donation.setName(Name.getText().toString());
        _donation.setShortDescription(shortDescription.getText().toString());
        _donation.setLongDescription(fullDescription.getText().toString());
        _donation.setValue(value);
        _donation.setCategory(Category.getSelectedItem().toString());
        _donation.setTime(LocalTime.now());
        _donation.setDate(LocalDate.now());
        _donation.setLocation(location);
        _donation.setId(location.size() + 1);

        Log.d("Edit", "Got new donation data: " + _donation);
        location.addDonation(_donation);
        model.addDonation(_donation);
//        saveList(location.getInventory(), location.getName());

        finish();
    }

    /**
     * Saves the list of donations.
     *
     * @param list the list of donations
     * @param key a key for the JSON object
     */
    public void saveList(List<Donation> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    /**
     * Returns the list of donations.
     *
     * @param key the key for the JSON object
     * @return the list of donations
     */
    public List<Donation> getList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<List<Donation>>() {}.getType();
        return gson.fromJson(json, type);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        _category = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { _category = "NA"; }
}

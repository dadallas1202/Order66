package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.DonationModel;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

/**
 * Activity to control the process of adding a new donation to the app.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 * Alayna Panlilio, Julia Tang
 *
 */
public class AddDonationActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

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
        Name = findViewById(R.id.nameDonationBox);
        shortDescription = findViewById(R.id.shortDescriptionBox);
        fullDescription = findViewById(R.id.fullDescriptionBox);
        Value = findViewById(R.id.valueBox);
        Category = findViewById(R.id.categorySpinner);

        /*
          Set up the adapter to display the allowable categories in the spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter(
                this,android.R.layout.simple_spinner_item, Donation.categories);
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
        Editable text0 = Value.getText();
        double value = Double.parseDouble(text0.toString());
        Editable text1 = Name.getText();
        _donation.setName(text1.toString());
        Editable text2 = shortDescription.getText();
        _donation.setShortDescription(text2.toString());
        Editable text3 = fullDescription.getText();
        _donation.setLongDescription(text3.toString());
        _donation.setValue(value);
        Object text4 = Category.getSelectedItem();
        _donation.setCategory(text4.toString());
        _donation.setTime(LocalTime.now());
        _donation.setDate(LocalDate.now());
        List<Donation> inv = location.getInventory();
        _donation.setViewId(inv.size() + 1);

        Log.d("Edit", "Got new donation data: " + _donation + " #" + _donation.getViewId());
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(
                getApplicationContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Object parent1 = parent.getItemAtPosition(position);
        _category = parent1.toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { _category = "NA"; }
}

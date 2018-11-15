package edu.mgrace31gatech.donationtracker.app.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;
    SearchView editsearch;
    Spinner locationSpinner;
    Spinner catergorySpinner;
    List<Location> locations1;
    List<String> locationName;
    List<Donation> allDonations;
    ArrayList<Donation> arraylist = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Generate sample data
        locations1 = LocationsModel.INSTANCE.getItems();
        locationName = new ArrayList<>();
        allDonations = new ArrayList<>();
        locationName.add("All Locations");

        // make a list of locations as well as list of all donations
        for (Location l : locations1) {
            locationName.add(l.getName());

            allDonations.addAll(l.getInventory());
        }

        // make a list of catergories
        List<String> catergories = Donation.categories;
        List<String> catergories2 = new ArrayList<>();
        catergories2.add("All Categories");
        catergories2.addAll(catergories);

        // Locate the ListView in listview_main.xml
        list = findViewById(R.id.listview);

        for (int i = 0; i < allDonations.size(); i++) {
            Donation d = new Donation(allDonations.get(i));

            // Binds all strings into an array
            arraylist.add(d);
            Log.d("My App", arraylist.toString());

        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        locationSpinner = findViewById(R.id.locSpinner);
        catergorySpinner = findViewById(R.id.catSpinner);

        adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, locationName);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter1);

        adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, catergories2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catergorySpinner.setAdapter(adapter2);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String location = locationSpinner.getSelectedItem().toString();
        String catergory = catergorySpinner.getSelectedItem().toString();
        String text = newText;
        adapter.filter(text, location, catergory);
        return false;
    }
}
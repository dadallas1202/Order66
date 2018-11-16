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

/**
 * Activity that allows for a user to search for donations within the app.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 *                                   Alayna Panlilio, Julia Tang
 */
public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListViewAdapter adapter;
    private Spinner locationSpinner;
    private Spinner categorySpinner;
    private final List<Donation> donationList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);

        // Generate sample data
        List<Location> locations1 = LocationsModel.INSTANCE.getItems();
        List<String> locationName = new ArrayList<>();
        List<Donation> allDonations = new ArrayList<>();
        locationName.add("All Locations");

        // make a list of locations as well as list of all donations
        for (Location l : locations1) {
            locationName.add(l.getName());

            allDonations.addAll(l.getInventory());
        }

        // make a list of categories
        List<String> categories = Donation.categories;
        List<String> categories2 = new ArrayList<>();
        categories2.add("All Categories");
        categories2.addAll(categories);

        ListView list = findViewById(R.id.listView);

        for (int i = 0; i < allDonations.size(); i++) {
            Donation d = new Donation(allDonations.get(i));

            // Binds all strings into an array
            donationList.add(d);
            Log.d("My App", donationList.toString());

        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, donationList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        SearchView editSearch = findViewById(R.id.search);
        editSearch.setOnQueryTextListener(this);

        locationSpinner = findViewById(R.id.locSpinner);
        categorySpinner = findViewById(R.id.catSpinner);

        ArrayAdapter<String> adapter1 = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, locationName);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, categories2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter2);
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Object location1 = locationSpinner.getSelectedItem();
        String location = location1.toString();
        Object category1 = categorySpinner.getSelectedItem();
        String category = category1.toString();
        adapter.filter(newText, location, category);
        return false;
    }
}
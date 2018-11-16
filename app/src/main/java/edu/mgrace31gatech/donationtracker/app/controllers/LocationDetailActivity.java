package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.mgrace31gatech.donationtracker.R;

/**
 * Activity that allows for a user to view the details of a location, and leads to the visibility of
 * the location's inventory list.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 *                                   Alayna Panlilio, Julia Tang
 */
public class LocationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        Button inventoryButton = findViewById(R.id.inventoryButton);
        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackBar = Snackbar.make(v, "Opening inventory", Snackbar.LENGTH_LONG);
                snackBar.setAction("Action", null);
                snackBar.show();
                Intent intent = new Intent(
                        LocationDetailActivity.this, InventoryListActivity.class);
                Intent parent = getIntent();
                int myExtras = parent.getIntExtra(
                        LocationDetailFragment.ARG_ITEM_ID, 1);
                intent.putExtra(LocationDetailFragment.ARG_ITEM_ID, myExtras);
                startActivity(intent);
            }
        });

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Log.d("MYAPP", "Location Detail Activity ");
            Bundle arguments = new Bundle();
            Intent myIntent = getIntent();
            int myExtras = myIntent.getIntExtra(LocationDetailFragment.ARG_ITEM_ID, 1);
            arguments.putInt(LocationDetailFragment.ARG_ITEM_ID,
                    myExtras);
            LocationDetailFragment fragment = new LocationDetailFragment();
            fragment.setArguments(arguments);
            FragmentManager myManager = getSupportFragmentManager();
            FragmentTransaction myTransaction = myManager.beginTransaction();
            myTransaction.add(R.id.location_detail_container, fragment);
            myTransaction.commit();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, LocationDetailActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
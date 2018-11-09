package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;

import edu.mgrace31gatech.donationtracker.R;

/**
 * An activity representing a single Location Inventory screen.
 *
 * Here we need to display a list of donations.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace, Alayna Panlilio, Julia Tang
 */

public class InventoryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Creating a new Donation", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getBaseContext(), AddDonationActivity.class);
                intent.putExtra(InventoryListFragment.ARG_LOCATION_ID,
                        getIntent().getIntExtra(InventoryListFragment.ARG_LOCATION_ID, 1));
                startActivity(intent);
            }
        });

        //Show the Up button in the action bar.
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        //savedInstanceState is non-null when there is fragment state
        //saved from previous configurations of this activity
        if (savedInstanceState == null) {
            // Create the list fragment and add it to the activity
            // using a fragment transaction.  Pass the location info to
            // the fragment
            Bundle arguments = new Bundle();
            arguments.putInt(InventoryListFragment.ARG_LOCATION_ID,
                    getIntent().getIntExtra(InventoryListFragment.ARG_LOCATION_ID, 1000));
            InventoryListFragment fragment = new InventoryListFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.inventory_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, LocationDetailActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

public class LocationDetailActivity extends AppCompatActivity {

    public Button InventoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        InventoryButton = (Button) findViewById(R.id.inventoryButton);
        InventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Opening inventory", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(LocationDetailActivity.this, InventoryListActivity.class);
                intent.putExtra(LocationDetailFragment.ARG_ITEM_ID, getIntent().getIntExtra(LocationDetailFragment.ARG_ITEM_ID, 1));
                startActivity(intent);
//                Log.d("MYAPP", "In Inventory Button");
//                Intent intent = new Intent(LocationDetailActivity.this, InventoryListActivity.class);
//                intent.putExtra(LocationDetailFragment.ARG_ITEM_ID, getIntent().getIntExtra(LocationDetailFragment.ARG_ITEM_ID, 1000));
//                startActivity(intent);
            }
        });

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Log.d("MYAPP", "Location Detail Activity ");
            Bundle arguments = new Bundle();
            arguments.putInt(LocationDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(LocationDetailFragment.ARG_ITEM_ID, 1));
            LocationDetailFragment fragment = new LocationDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.location_detail_container, fragment)
                    .commit();
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
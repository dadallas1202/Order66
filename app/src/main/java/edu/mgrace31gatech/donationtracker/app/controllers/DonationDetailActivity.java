package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentActivity;

import edu.mgrace31gatech.donationtracker.R;

public class DonationDetailActivity extends Activity {

    private FragmentActivity myContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);

        if(savedInstanceState == null) {
            //Create the detail fragment and add it to the activity
            //using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(DonationDetailFragment.ARG_DONATION_ID,
                    getIntent().getIntExtra(DonationDetailFragment.ARG_DONATION_ID,1));
            DonationDetailFragment fragment = new DonationDetailFragment();
            fragment.setArguments(arguments);
            myContext.getSupportFragmentManager().beginTransaction().add(R.id.donation_detail_container, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //This ID represents the Home or Up button. In the case of this
            //activity, the Up button is shown. For more details,
            //see the Navigation pattern on Android Design:
            //
            //http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, DonationDetailActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

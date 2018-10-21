package edu.mgrace31gatech.donationtracker.app.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.InventoryModel;

/**
 * A fragment representing a single DataItem detail screen.
 * This fragment is either contained in a {@link InventoryActivity}
 * in two-pane mode (on tablets) or a {@link DonationDetailActivity}
 * on handsets.
 */
public class DonationDetailFragment extends Fragment {

    /**
     * The fragment argument representing the item ID that this
     * fragment represents.
     */
    public static final String ARG_DONATION_ID = "donation_key";

    /**
     * The dummy content this fragment is presenting.
     */
    private Donation mDonation;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate
     * the fragment (e.g. upon screen orientation changes).
     */
    public DonationDetailFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_DONATION_ID)) {
            //Load the dummy content specified by the fragment
            //arguments. In a read-world scenario, use a Loader
            //to load content from a content provider.

            int donation_key = getArguments().getInt(ARG_DONATION_ID);
            Log.d("MYAPP", "Start details for: " + donation_key);
            mDonation = InventoryModel.INSTANCE.findDonationById(donation_key);
            if(mDonation == null) {
                Log.d("MYAPP", "Null Donation: " + donation_key);
            } else {
                Log.d("MYAPP", "Not Null Donation: " + donation_key);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.inventory_list, container, false);
        Log.d("MYAPP", "Getting ready to set data");
        if(mDonation != null) {
            ((TextView) rootView.findViewById(R.id.nameDonation)).setText(mDonation.getName());
            ((TextView) rootView.findViewById(R.id.shortDescription)).setText(mDonation.getShortDescription());
            ((TextView) rootView.findViewById(R.id.fullDescription)).setText(mDonation.getLongDescription());
            ((TextView) rootView.findViewById(R.id.value)).setText(String.valueOf(mDonation.getValue()));
            ((TextView) rootView.findViewById(R.id.category)).setText(mDonation.getCategory());
            ((TextView) rootView.findViewById(R.id.date)).setText(mDonation.getDate());
            ((TextView) rootView.findViewById(R.id.time)).setText(mDonation.getTime());
        }
        return rootView;
    }
}

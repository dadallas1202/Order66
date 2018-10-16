package edu.mgrace31gatech.donationtracker.app.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

/**
 * A fragment representing a single DataItem detail screen.
 * This fragment is either contained in a {@link LocationList}
 * in two-pane mode (on tablets) or a {@link LocationDetailActivity}
 * on handsets.
 */
public class LocationDetailFragment extends Fragment {

    /**
     * The fragment argument representing the ite5m ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "location_key";

    /**
     * The dummy content this fragment is presenting.
     */
    private Location mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

            int location_key = getArguments().getInt(ARG_ITEM_ID);
            Log.d("MYAPP", "Start details for: " + location_key);
            mItem = LocationsModel.INSTANCE.findLocationByKey(location_key);
            if (mItem == null) {
                Log.d("MYAPP", "Null:" + location_key);
            } else {
                Log.d("MYAPP", "Not null " + location_key);
            }
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_detail, container, false);
        Log.d("MYAPP", "Getting ready to set data");
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            Log.d("MYAPP", "Getting ready to set id");
            ((TextView) rootView.findViewById(R.id.name)).setText("" + mItem.getName());
            Log.d("MYAPP", "Getting ready to set name :");
            ((TextView) rootView.findViewById(R.id.type)).setText(mItem.getType());
            ((TextView) rootView.findViewById(R.id.lat)).setText(mItem.getLattitude());
            ((TextView) rootView.findViewById(R.id.lon)).setText(mItem.getLongitude());
            ((TextView) rootView.findViewById(R.id.address)).setText(mItem.getAddress());
            ((TextView) rootView.findViewById(R.id.phone)).setText(mItem.getPhone());
        }
        return rootView;
    }

}

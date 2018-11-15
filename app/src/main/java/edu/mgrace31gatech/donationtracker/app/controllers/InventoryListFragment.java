package edu.mgrace31gatech.donationtracker.app.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.DonationModel;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

import static edu.mgrace31gatech.donationtracker.app.controllers.DonationDetailFragment.ARG_DON_ID;

/**
 * A fragment representing a single Location Inventory screen.
 *
 * Basically this displays a list of donations that are in a particular
 * location that was selected from the location list screen.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace, Alayna Panlilio, Julia Tang
 */

public class InventoryListFragment extends Fragment {
    /**
     * The fragment arguments representing the ID's that this fragment
     * represents. Used to pass keys into other activities through Bundle/Intent
     */
    public static final String ARG_LOCATION_ID = "location_id";
    public static final String ARG_DONATION_ID = "donation_id";

    /**
     * The location that this inventory list is for.
     */
    private Location mLocation;

    private List<Donation> mDonations;

    /**
     * The adapter for the recycle view list of donations
     */
    private SimpleDonationRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment
     */
    public InventoryListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if we got a valid location passed to us
        if (Objects.requireNonNull(getArguments()).containsKey(ARG_LOCATION_ID)) {
            //Get the id from the intent arguments (bundle) and
            //ask the model to give us the location object

            LocationsModel model = LocationsModel.getInstance();
            mLocation = model.getCurrentLocation();
            DonationModel model2 = DonationModel.getInstance();
            mDonations = mLocation.getInventory();
                    //getList(mLocation.getName()) == null ? new ArrayList<Donation>() : getList(mLocation.getName());
            model2.addInventoryToLocation(mLocation, mDonations);


            Log.d("InventoryListFragment", "Passing over location: " + mLocation);
            Log.d("InventoryListFragment", "Got donations: " + mLocation.getInventory().size());

            Activity activity = this.getActivity();

            CollapsingToolbarLayout appBarLayout = Objects.requireNonNull(activity).findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mLocation.toString());
            }
        }
    }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.inventory_list, container, false);

            //Step 1. Setup the recycler view by getting it from our layout in the main window
            View recyclerView = rootView.findViewById(R.id.donation_list);
            assert recyclerView != null;
            //Step 2. Hook up the adapter to the view
            setupRecyclerView((RecyclerView) recyclerView);

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            adapter.notifyDataSetChanged();
        }

        /**
         * Set up an adapter and hook it to the provided view
         *
         * @param recyclerView the view that needs this adapter
         */
        private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
            adapter = new SimpleDonationRecyclerViewAdapter(mLocation.getInventory());
            Log.d("Adapter", adapter.toString());
            recyclerView.setAdapter(adapter);
        }

    /**
     * Returns the list of donations for the location.
     *
     * @param key the key for the JSON object
     * @return a list of donations for that location
     */
    public List<Donation> getList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getActivity()).getBaseContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<List<Donation>>() {}.getType();
        return gson.fromJson(json, type);
    }

        /**
         * This inner class is our custom adapter. It takes our basic model information and
         * converts it to the correct layout for this view.
         *
         * In this case, we are just mapping the toString to the Donation object to a text field.
         */
        public class SimpleDonationRecyclerViewAdapter
                extends RecyclerView.Adapter<SimpleDonationRecyclerViewAdapter.ViewHolder> {

            /**
             * Collection of the items to be shown in this list.
             */
            //private final List<Donation> mValues;


            /**
             * set the items to be used by the adapter
             *
             * @param items the list of items to be displayed in the recycler view
             */
            public SimpleDonationRecyclerViewAdapter(List<Donation> items) {
                mDonations = items;
            }

            @NonNull
            @Override
            public SimpleDonationRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                /*
                  This sets up the view for each individual item in the recycler display
                  To edit the actual layout, we would look at: res/layout/inventory_list_content.xml
                  If you look at the example file, you will see it currently just 2 TextView elements
                */
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.inventory_list_content, parent, false);
                return new SimpleDonationRecyclerViewAdapter.ViewHolder(view);
            }
            @Override
            public void onBindViewHolder(@NonNull final SimpleDonationRecyclerViewAdapter.ViewHolder holder, int position) {

                //final LocationsModel model = LocationsModel.getInstance();
                /*
                This is where we have to bind each data element in the list (given by position parameter)
                to an element in the view (which is one of our two TextView widgets
                  */
                //start by getting the element at the correct position
                holder.mDonation = mDonations.get(position);
                Log.d("Adapter", "donation: " + holder.mDonation);
                /*
                  Now we bind the data to the widgets. In this case, pretty simple, put the id in one
                  textview and the string rep of a course in the other.
                 */
                holder.mIdView.setText(mDonations.get(position).getViewId());
                holder.mContentView.setText(mDonations.get(position).toString());

                /*
                 * set up a listener to handle if the user clicks on this list item, what should happen?
                 */
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //on a phone, we need to change windows to the detail view
                        Context context = v.getContext();
                        //create our new intent with the new screen (activity)
                        Intent intent = new Intent(context, DonationDetailActivity.class);
                            /*
                                pass along the selected donation we can retrieve the correct data in
                                the next window
                             */
                        intent.putExtra(ARG_DON_ID, holder.mDonation.getId());

                        //now just display the new window
                        context.startActivity(intent);

                    }
                });
            }

            @Override
            public int getItemCount() { return mDonations.size(); }

            /**
             * This inner class represents a ViewHolder which provides us a way to cache information
             * about the binding between the model element (in this case a Location) and the widgets in
             * the list view (in this case the two TextView)
             */
            public class ViewHolder extends RecyclerView.ViewHolder {
                public final View mView;
                public final TextView mIdView;
                public final TextView mContentView;
                public Donation mDonation;

                public ViewHolder(View view) {
                    super(view);
                    mView = view;
                    mIdView = view.findViewById(R.id.id2);
                    Log.d("Holder", mIdView.toString());
                    mContentView = view.findViewById(R.id.content2);
                }

                @Override
                public String toString() {
                    return super.toString() + " '" + mContentView.getText() + "'";
                }
            }
        }
}

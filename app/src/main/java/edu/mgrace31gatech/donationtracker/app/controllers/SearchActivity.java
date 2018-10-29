package edu.mgrace31gatech.donationtracker.app.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        EditText searchBar = (EditText) findViewById(R.id.searchView);
        Spinner locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        Spinner catSpinner = (Spinner) findViewById(R.id.catSpinner);


        List<Location> locations = LocationsModel.INSTANCE.getItems();
        List<String> locationName = new ArrayList<>();
        List<Donation> allDonations = new ArrayList<>();
        locationName.add("All Locations");

        // make a list of locations as well as list of all donations
        for (Location l : locations) {
            locationName.add(l.getName());
            for (Donation d : l.getInventory()) {
                allDonations.add(d);
            }
        }

        // make a list of catergories
        List<String> catergories = Donation.categories;
        List<String> catergories2 = new ArrayList<>();
        catergories2.add("All Catergories");
        for (String c : catergories) {
            catergories2.add(c);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, locationName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, catergories2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter2);

        View recyclerView = findViewById(R.id.donationRecycle);
        assert recyclerView != null;
//        setupRecyclerView((RecyclerView) recyclerView);
    }
//    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
//        recyclerView.setAdapter(new LocationList.SimpleItemRecyclerViewAdapter(LocationsModel.INSTANCE.getItems()));
//    }
//    public class SimpleItemRecyclerViewAdapter
//            extends RecyclerView.Adapter<LocationList.SimpleItemRecyclerViewAdapter.ViewHolder> {
//
//        private final List<Location> mValues;
//
//        public SimpleItemRecyclerViewAdapter(List<Location> locations) {
//            mValues = locations;
//        }
//
//        @Override
//        public LocationList.SimpleItemRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.location_list_content, parent, false);
//            return new LocationList.SimpleItemRecyclerViewAdapter.ViewHolder(view);
//        }
//
//
//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//
//        @Override
//        public void onBindViewHolder(final LocationList.SimpleItemRecyclerViewAdapter.ViewHolder holder, int position) {
//            holder.mItem = mValues.get(position);
//            holder.mKeyView.setText("" + mValues.get(position).getKey());
//            holder.mContentView.setText(mValues.get(position).getName());
//
//            final LocationsModel model = LocationsModel.getInstance();
//
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Context context = v.getContext();
//                    Intent intent = new Intent(context, LocationDetailActivity.class);
//                    Log.d("MYAPP", "Switch to detailed view for item: " + holder.mItem.getName());
//                    Log.d("MYAPP", "Switch to detailed view for item: " + holder.mItem.getKey());
//                    intent.putExtra(LocationDetailFragment.ARG_ITEM_ID, holder.mItem.getKey());
//                    model.setCurrentLocation(holder.mItem);
//                    context.startActivity(intent);
//                }
//            });
//        }
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//            public final TextView mKeyView;
//            public final TextView mContentView;
//            public Location mItem;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mKeyView = (TextView) view.findViewById(R.id.id);
//                mContentView = (TextView) view.findViewById(R.id.content);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mContentView.getText() + "'";
//            }
//        }
//    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

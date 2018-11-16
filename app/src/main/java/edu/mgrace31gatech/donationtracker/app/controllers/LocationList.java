package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

/**
 * Activity that displays a list of the Locations available for donations.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 *                                   Alayna Panlilio, Julia Tang
 */
public class LocationList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        View recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(
                new SimpleItemRecyclerViewAdapter(LocationsModel.INSTANCE.getItems()));
    }
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Location> mValues;

        SimpleItemRecyclerViewAdapter(List<Location> locations) {
            mValues = locations;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.location_list_content, parent, false);
            return new ViewHolder(view);
        }


        @Override
        public int getItemCount() {
            return mValues.size();
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            Location myPosition = mValues.get(position);
            String id = Integer.toString(myPosition.getKey());
            holder.mKeyView.setText(id);
            Location locationName = mValues.get(position);
            holder.mContentView.setText(locationName.getName());

            final LocationsModel model = LocationsModel.getInstance();

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, LocationDetailActivity.class);
                    Log.d("MYAPP", "Switch to detailed view for item: " + holder.mItem.getName());
                    Log.d("MYAPP", "Switch to detailed view for item: " + holder.mItem.getKey());
                    intent.putExtra(LocationDetailFragment.ARG_ITEM_ID, holder.mItem.getKey());
                    model.setCurrentLocation(holder.mItem);
                    context.startActivity(intent);
                }
            });
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            final View mView;
            final TextView mKeyView;
            final TextView mContentView;
            Location mItem;

            ViewHolder(View view) {
                super(view);
                mView = view;
                mKeyView = view.findViewById(R.id.id);
                mContentView = view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.InventoryModel;

public class InventoryActivity extends Activity {

    private FloatingActionButton addDonation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        View recyclerView = findViewById(R.id.dataitem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        addDonation = (FloatingActionButton) findViewById(R.id.addDonationButton);
        addDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InventoryActivity.this, DonationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(InventoryModel.INSTANCE.getItems()));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<InventoryActivity.SimpleItemRecyclerViewAdapter.ViewHolder> {
        @NonNull
        private static final String TAG = "RecyclerViewAdapter";
        private List<Donation> mDonationNames;

        public SimpleItemRecyclerViewAdapter(List<Donation> nameDonation) {
            mDonationNames = nameDonation;
        }

        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_inventory, parent, false);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mDonation = mDonationNames.get(position);
            holder.mDonationView.setText(mDonationNames.get(position).getName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DonationDetailActivity.class);
                    intent.putExtra(DonationDetailFragment.ARG_DONATION_ID, holder.mDonation.getId());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDonationNames.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public final View mView;
            public final TextView mDonationView;
            public Donation mDonation;

            public ViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
                mDonationView = (TextView) itemView.findViewById(R.id.nameofdonation);
            }
        }
    }
}

package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

import static edu.mgrace31gatech.donationtracker.app.controllers.DonationDetailFragment.ARG_DON_ID;

class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    private final LayoutInflater inflater;
    private final List<Donation> allDonations;
    private final List<Donation> arrayList;

    public ListViewAdapter(Context context, List<Donation> allDonations) {
        this.allDonations = allDonations;
        inflater = LayoutInflater.from(context);
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(allDonations);
    }

    class ViewHolder {
        TextView name;
        Donation mDonation;
    }

    @Override
    public int getCount() {
        return allDonations.size();
    }

    @Override
    public Donation getItem(int position) {
        return allDonations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        View view1 = view;
        final ViewHolder holder;
        if (view1 == null) {
            holder = new ViewHolder();
            view1 = inflater.inflate(R.layout.listview_item, parent);
            holder.name = view1.findViewById(R.id.name);
            view1.setTag(holder);
        } else {
            holder = (ViewHolder) view1.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(allDonations.get(position).getName());
        holder.mDonation = allDonations.get(position);
        Log.d("Adapter", "donation: " + holder.mDonation);

        /*
         * set up a listener to handle if the user clicks on this list item, what should happen?
         */
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DonationDetailActivity.class);
                intent.putExtra(ARG_DON_ID, holder.mDonation.getId());

                //now just display the new window
                context.startActivity(intent);

            }
        });

        return view1;
    }

    // Filter Class
    public void filter(String charText, String location, String category) {
        List<Location> locations = LocationsModel.INSTANCE.getItems();
        Collection<Donation> lHelper = new ArrayList<>();
        Collection<Donation> cHelper = new ArrayList<>();

        String charText1 = charText.toLowerCase(Locale.getDefault());

        allDonations.clear();
        lHelper.clear();
        cHelper.clear();
        if ("All Locations".equals(location)) {
            if ("All Categories".equals(category)) {
                if (charText1.isEmpty()) {
                    allDonations.addAll(arrayList);
                } else {
                    for (Donation wp : arrayList) {
                        if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText1)
                                && !allDonations.contains(wp)) {
                            allDonations.add(wp);
                        }
                    }
                }
            } else {
                for (Donation d : arrayList) {
                    if (d.getCategory().equals(category) && !cHelper.contains(d)) {
                        cHelper.add(d);
                    }
                }
                if (charText1.isEmpty()) {
                    for (Donation d : cHelper) {
                        if (!allDonations.contains(d)) {
                            allDonations.add(d);
                        }
                    }
                } else {
                    for (Donation wp : cHelper) {
                        if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText1)
                                && !allDonations.contains(wp)) {
                            allDonations.add(wp);
                        }
                    }
                }
            }
            if (allDonations.isEmpty()) {
                allDonations.add(new Donation("No Donations found"));
            }
        } else {
            for(Location l: locations) {
                if(l.getName().equals(location)) {
                    for (Donation d : l.getInventory()) {
                        if (!lHelper.contains(d)) {
                            lHelper.add(d);
                        }
                    }
                }
                if ("All Categories".equals(category)) {
                    if (charText1.isEmpty()) {
                        for (Donation d : lHelper) {
                            if (!allDonations.contains(d)) {
                                allDonations.add(d);
                            }
                        }
                    } else {
                        for (Donation wp : lHelper) {
                            if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText1)
                                    && !allDonations.contains(wp)) {
                                allDonations.add(wp);
                            }
                        }
                    }
                } else {
                    for (Donation d : lHelper) {
                        if (d.getCategory().equals(category) && !cHelper.contains(d)) {
                            cHelper.add(d);
                        }
                    }
                    if (charText1.isEmpty()) {
                        for (Donation d : cHelper) {
                            if (!allDonations.contains(d)) {
                                allDonations.add(d);
                            }
                        }
                    } else {
                        for (Donation wp : cHelper) {
                            if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText1)
                                    && !allDonations.contains(wp)) {
                                allDonations.add(wp);
                            }
                        }
                    }
                }

            }
            if (allDonations.isEmpty()) {
                allDonations.add(new Donation("No Donations found"));
            }
        }

        notifyDataSetChanged();
    }

}
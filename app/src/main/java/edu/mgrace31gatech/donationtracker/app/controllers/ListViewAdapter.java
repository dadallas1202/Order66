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

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private List<Donation> allDonations = null;
    private List<Donation> arraylist;

    public ListViewAdapter(Context context, List<Donation> allDonations) {
        mContext = context;
        this.allDonations = allDonations;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Donation>();
        this.arraylist.addAll(allDonations);
    }

    public class ViewHolder {
        TextView name;
        public Donation mDonation;
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


    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(allDonations.get(position).getName());
        holder.mDonation = allDonations.get(position);
        Log.d("Adapter", "donation: " + holder.mDonation);

        /*
         * set up a listener to handle if the user clicks on this list item, what should happen?
         */
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DonationDetailActivity.class);
                intent.putExtra(ARG_DON_ID, holder.mDonation.getId());

                //now just display the new window
                context.startActivity(intent);

            }
        });

        return view;
    }

    // Filter Class
    public void filter(String charText, String location, String catergory) {
        List<Location> locations = LocationsModel.INSTANCE.getItems();
        Collection<Donation> lHelper = new ArrayList<>();
        List<Donation> cHelper = new ArrayList<>();

        charText = charText.toLowerCase(Locale.getDefault());

        allDonations.clear();
        lHelper.clear();
        cHelper.clear();
        if (location.equals("All Locations")) {
            if (catergory.equals("All Categories")) {
                if (charText.length() == 0) {
                    allDonations.addAll(arraylist);
                } else {
                    for (Donation wp : arraylist) {
                        if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText) && !allDonations.contains(wp)) {
                            allDonations.add(wp);
                        }
                    }
                }
            } else {
                for (Donation d : arraylist) {
                    if (d.getCategory().equals(catergory) && !cHelper.contains(d)) {
                        cHelper.add(d);
                    }
                }
                if (charText.length() == 0) {
                    for (Donation d : cHelper) {
                        if (!allDonations.contains(d)) {
                            allDonations.add(d);
                        }
                    }
                } else {
                    for (Donation wp : cHelper) {
                        if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText) && !allDonations.contains(wp)) {
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
                if (catergory.equals("All Categories")) {
                    if (charText.length() == 0) {
                        for (Donation d : lHelper) {
                            if (!allDonations.contains(d)) {
                                allDonations.add(d);
                            }
                        }
                    } else {
                        for (Donation wp : lHelper) {
                            if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText) && !allDonations.contains(wp)) {
                                allDonations.add(wp);
                            }
                        }
                    }
                } else {
                    for (Donation d : lHelper) {
                        if (d.getCategory().equals(catergory) && !cHelper.contains(d)) {
                            cHelper.add(d);
                        }
                    }
                    if (charText.length() == 0) {
                        for (Donation d : cHelper) {
                            if (!allDonations.contains(d)) {
                                allDonations.add(d);
                            }
                        }
                    } else {
                        for (Donation wp : cHelper) {
                            if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText) && !allDonations.contains(wp)) {
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
package edu.mgrace31gatech.donationtracker.app.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.mgrace31gatech.donationtracker.app.controllers.LocationDetailFragment;

public class InventoryModel {

    public static final InventoryModel INSTANCE = new InventoryModel();

    private List<Donation> donationList;

    private InventoryModel() { donationList = new ArrayList<>();
    }

    public void addDonation(Donation donation) {
        donationList.add(donation);
    }

    public List<Donation> getItems() {
        return donationList;
    }

    public Donation findDonationById(int id) {
        for (Donation l : donationList) {
            if (l.getId() == id) return l;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }
}

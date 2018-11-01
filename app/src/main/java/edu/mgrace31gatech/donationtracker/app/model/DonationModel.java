package edu.mgrace31gatech.donationtracker.app.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DonationModel {
    /** Singleton instance */
    private static final DonationModel _instance = new DonationModel();
    public static DonationModel getInstance() { return _instance; }

    /** holds the list of all donations */
    private List<Donation> _donations;

    private Location _currentLocation;
    /** the currently selected donation, defaults to the first donation */
    private Donation _currentDonation;

    /**
     * make a new model
     */
    private DonationModel() {
        _donations = new ArrayList<>();
    }

    /**
     * add a donation to the app.
     * @param donation the donation to be added
     */
    public void addDonation(Donation donation) {
        _donations.add(donation);
    }

    public void addInventoryToLocation(Location location, List<Donation> listDonation ) {
        _currentLocation = location;
        _currentLocation.setInventory(listDonation);
    }

    public Donation getCurrentDonation() { return _currentDonation; }

    public List<Donation> getInventory() { return _donations; }

    public Donation findDonationById(int id) {
        for (Donation d : _donations) {
            if (d.getId() == id) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }

    public void setCurrentDonation(Donation currentDonation) {
        this._currentDonation = currentDonation;
    }
}

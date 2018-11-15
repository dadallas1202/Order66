package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DonationModel {
    /** Singleton instance */
    private static final DonationModel _instance = new DonationModel();
    public static DonationModel getInstance() { return _instance; }

    /** holds the list of all donations */
    private final List<Donation> _donations;

    /** the currently selected donation, defaults to the first donation */
    private Donation _currentDonation;

    /**
     * make a new model
     */
    public DonationModel() {
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
        Location _currentLocation = location;
        _currentLocation.setInventory(listDonation);
    }

    public Donation getCurrentDonation() { return _currentDonation; }

    public Donation getDonation(int index) { return _donations.get(index); }

    public List<Donation> getInventory() { return Collections.unmodifiableList(_donations); }

    public Donation findDonationById(int id) {
        for (Donation d : _donations) {
            if (d.getId() == id) {
                return d;
            }
        }
        //Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }

    public void setCurrentDonation(Donation currentDonation) {
        this._currentDonation = currentDonation;
    }
}

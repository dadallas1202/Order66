package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class for the Donation class, deals with everything that handles donations within the app.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 *                                   Alayna Panlilio, Julia Tang
 */
public class DonationModel {
    /** Singleton instance */
    private static final DonationModel _instance = new DonationModel();

    /**
     * Returns the current instance of the DonationModel.
     *
     * @return current DonationModel instance
     */
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

    /**
     * Adds a list of donations to a specific location.
     *
     * @param location a specific location
     * @param listDonation a list of donations
     */
    public void addInventoryToLocation(Location location, List<Donation> listDonation) {
        Location _currentLocation = location;
        _currentLocation.setInventory(listDonation);
    }

    //public Donation getCurrentDonation() { return _currentDonation; }

    /**
     * Returns the donation at the specified index.
     *
     * @param index the specified index
     * @return the donation at the index
     */
    public Donation getDonation(int index) { return _donations.get(index); }

    //public List<Donation> getInventory() { return Collections.unmodifiableList(_donations); }

    /**
     * Finds a donation by the specified ID
     *
     * @param id an integer identification number
     * @return a Donation that matches the ID
     */
    public Donation findDonationById(int id) {
        for (Donation d : _donations) {
            if (d.getId() == id) {
                return d;
            }
        }
        //Log.d("MYAPP", "Warning - Failed to find id: " + id);
        return null;
    }

//    public void setCurrentDonation(Donation currentDonation) {
//        this._currentDonation = currentDonation;
//    }
}

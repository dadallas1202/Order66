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
        location.setInventory(listDonation);
    }

    /**
     * Returns the donation at the specified index.
     *
     * @param index the specified index
     * @return the donation at the index
     */
    public Donation getDonation(int index) { return _donations.get(index); }

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
        return null;
    }
}

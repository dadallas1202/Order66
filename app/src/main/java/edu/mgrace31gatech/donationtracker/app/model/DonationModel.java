package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.List;

public class DonationModel {
    /** Singleton instance */
    private static final DonationModel _instance = new DonationModel();
    public static DonationModel getInstance() { return _instance; }

    /** holds the list of all donations */
    private List<Donation> _donations;
    /** the currently selected donation, defaults to the first donation */
    private Donation _currentDonation;

    /**
     * make a new model
     */
    private DonationModel() {
        _donations = new ArrayList<>();
    }

    public Donation getCurrentDonation() { return _currentDonation; }
}

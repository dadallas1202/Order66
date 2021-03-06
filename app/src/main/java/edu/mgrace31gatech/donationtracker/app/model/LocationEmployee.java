package edu.mgrace31gatech.donationtracker.app.model;

import java.util.Collection;

public class LocationEmployee extends RegisteredUser {
    private Location loc;

    public LocationEmployee(String name, String userName, String password, Location loc) {
        super(name, userName, password, false);
        this.loc = loc;
    }

    public void addDonation(Donation d) {
        loc.addDonation(d);
    }

    public Location getLocation() {
        return loc;
    }
}

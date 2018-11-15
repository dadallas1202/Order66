package edu.mgrace31gatech.donationtracker.app.model;

class LocationEmployee extends RegisteredUser {
    private final Location loc;

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

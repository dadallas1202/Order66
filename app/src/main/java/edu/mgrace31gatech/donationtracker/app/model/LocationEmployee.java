package edu.mgrace31gatech.donationtracker.app.model;

import java.util.Collection;

public class LocationEmployee extends RegisteredUser {
    private Location loc;

    public LocationEmployee(String name, String userName, String password, String locationName) {
        super(name, userName, password, false, true);
        LocationsModel model = LocationsModel.INSTANCE;
        for (Location l : model.getLocations()) {
            if (l.getName().equals(locationName)) {
                this.loc = l;
            }
        }
    }


//    public void addDonation(Donation d) {
//        loc.addDonation(d);
//    }

    public Location getLocation() {
        return loc;
    }
}

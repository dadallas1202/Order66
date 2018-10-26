package edu.mgrace31gatech.donationtracker.app.model;

import android.util.Log;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LocationsModel {
    /** Singleton instance */
    public static final LocationsModel INSTANCE = new LocationsModel();
    public static LocationsModel getInstance() { return INSTANCE; }

    /** holds the list of all locations */
    private List<Location> locationsList;
    private List<Donation> donationsList;

    /** the currently selected location, defaults to first location */
    private Location currentLocation;

    /**
     * make a new model
     */
    private LocationsModel() {
            locationsList = new ArrayList<>();
    }

    /**
     * get the locations
     * @return a list of the locations in the app
     */
    public List<Location> getItems() {
            return locationsList;
    }

    /**
     * add a location to the app.
     * @param location the location to be added
     */
    public void addLocation(Location location) {
        locationsList.add(location);
    }

    /**
     *
     * @return the currently selected location
     */
    public Location getCurrentLocation() { return currentLocation; }

    public void setCurrentLocation(Location location) { currentLocation = location; }

    /**
     * Return a location that has a matching key.
     * @param key the key of the location to find
     * @return the location with that key or null if no such key exists.
     */
    public Location findLocationByKey(int key) {
        for (Location l : locationsList) {
            if (l.getKey() == key) return l;
        }
        Log.d("MYAPP", "Warning - Failed to find id: " + key);
        return null;
    }


    /**
     * add a donation to the current location inventory
     *
     * @param donation the donation to add
     * @return true if donation added, false if not added
     */
    public boolean addDonation(Donation donation) {
        return currentLocation != null;
    }
}

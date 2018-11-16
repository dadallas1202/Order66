package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Model class for the Location class, which handles everything that involves the Locations
 * within the app.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 *                                   Alayna Panlilio, Julia Tang
 */
public final class LocationsModel {
    /** Singleton instance */
    public static final LocationsModel INSTANCE = new LocationsModel();

    /**
     * Returns the current instance of the LocationModel.
     *
     * @return the current LocationModel instance
     */
    public static LocationsModel getInstance() { return INSTANCE; }

    private static Location theLastAddedElement;

    /** holds the list of all locations */
    private static List<Location> locationsList;
//    private List<Donation> donationsList;

    /** the currently selected location, defaults to first location */
    private Location currentLocation;

    /**
     * make a new model
     */
    public LocationsModel() {
            locationsList = new ArrayList<>();
    }

    /**
     * get the locations
     * @return a list of the locations in the app
     */
    public List<Location> getItems() {
            return Collections.unmodifiableList(locationsList);
    }

    /**
     * Sets and fills in the list of locations.
     *
     * @param list a list of locations
     */
    public void setItems(List<Location> list) { locationsList = list; }

    /**
     * Add a location to the app.
     * @param location the location to be added
     *
     * @return whether or not the location was added or not
     */
    public static boolean addLocation(Location location) {
           for (Location l :locationsList ) {
               System.out.println(l.getName());
            String name = l.getName();
            String latitude = l.getLatitude();
            if (name.equals(location.getName())
                    && latitude.equals(location.getLatitude())) {
                return false;
            }
        }
        locationsList.add(location);
        theLastAddedElement = location;
        return true;
    }

    /**
     *
     * @return the currently selected location
     */
    public Location getCurrentLocation() { return currentLocation; }

    /**
     * Sets currentLocation to the passed in location
     *
     * @param location the desired current location
     */
    public void setCurrentLocation(Location location) { currentLocation = location; }

    /**
     * Return a location that has a matching key.
     * @param key the key of the location to find
     * @return the location with that key or null if no such key exists.
     */
    public Location findLocationByKey(int key) {
        for (Location l : locationsList) {
            if (l.getKey() == key) {
                return l;
            }
        }
        return null;
    }
    /**
     * clear the list. USED FOR TESTING PURPOSES
     * @return a empty list
     */
    public List<Location> clear() {
        for (Location l: locationsList) {
            locationsList.remove(l);
        }
        return locationsList;
    }
}

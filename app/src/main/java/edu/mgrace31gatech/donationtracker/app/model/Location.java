package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a location.
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas,
 * Marie Grace, Alayna Panlilio, Julia Tang
 */
public class Location {
    private final int key;
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String address;
    private final String type;
    private final String phone;
    private final String city;
    private final String state;
    private final String zip;
    private final String website;
    private List<Donation> inventory;

    /**
     * Creates a location.
     *
     * @param k   The key of the location.
     * @param n   The name of the location.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @param a   The address of the location.
     * @param c   The city of the location.
     * @param s   The state of the location.
     * @param z   The zip-code of the location.
     * @param t   The type of the location.
     * @param p   The phone number of the location.
     * @param w   The website of the location.
     */
    public Location(int k, String n, String lat, String lon, String a,String c, String s,
                    String z, String t, String p, String w) {
        key = k;
        name = n;
        type = t;
        longitude = lon;
        latitude = lat;
        address = a;
        phone = p;
        city = c;
        state = s;
        zip = z;
        website = w;
        inventory = new ArrayList<>();
    }

    /**
     * Gets the name of the location.
     * @return The name of the location.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the key of the location.
     * @return The key of the location.
     */
    public int getKey() {
        return key;
    }

    /**
     * Gets the type of the location.
     * @return The type of the location.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the latitude of the location.
     * @return The latitude of the location.
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the location.
     * @return The longitude of the location.
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Gets the phone number of the location.
     * @return The phone number of the location.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the address of the location.
     * @return The address of the location.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the inventory of the location.
     * @return The inventory list of donations for the location.
     */
    public List<Donation> getInventory() { return Collections.unmodifiableList(inventory); }

    /**
     * Sets the inventory of the location.
     * @param donationList The inventory list of donations for the location.
     */
    public void setInventory(List<Donation> donationList) { inventory = donationList; }

    /**
     * Adds a donation to the inventory of the location.
     * @param d The donation to be added.
     */
    public void addDonation(Donation d) {
        inventory.add(d);
    }

    /**
     * Gets the size of the inventory.
     * @return The size of the inventory of the location.
     */
    public int size() {
        return inventory.size();
    }

    /**
     * Converts the name of the location to a string.
     * @return The name of the location.
     */
    @Override
    public String toString() { return name; }
    
}

package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Location {
    private final int key;
    private final String name;
    private final String latitude;
    private final String longitude;
    private final String address;
    private final String city;
    private final String state;
    private final String zip;
    private final String type;
    private final String phone;
    private final String website;
    private List<Donation> inventory;

    public Location(int k, String n, String lat, String lon, String a,String c, String s, String z, String t, String p, String w) {
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

    public String getName() {
        return name;
    }

    public int getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public List<Donation> getInventory() { return Collections.unmodifiableList(inventory); }

    public void setInventory(List<Donation> donationList) { inventory = donationList; }

    public void addDonation(Donation d) {
        inventory.add(d);
    }

    public int size() {
        return inventory.size();
    }

    @Override
    public String toString() { return name; }
    
}

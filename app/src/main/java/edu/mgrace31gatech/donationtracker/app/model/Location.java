package edu.mgrace31gatech.donationtracker.app.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Location {
    private int key;
    private String name;
    private String latitude;
    private String longitude;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;
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

    public List<Donation> getInventory() { return inventory; }

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

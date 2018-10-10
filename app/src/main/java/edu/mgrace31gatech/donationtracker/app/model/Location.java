package edu.mgrace31gatech.donationtracker.app.model;

public class Location {
    private String key;
    private String name;
    private String lattitude;
    private String longitude;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String type;
    private String phone;
    private String website;

    public Location(String k, String n, String lon, String lat, String a,String c, String s, String z, String t, String p, String w) {
        key = k;
        name = n;
        type = t;
        longitude = lon;
        lattitude = lat;
        address = a;
        phone = p;
        city = c;
        state = s;
        zip = z;
        website = w;

    }

    public String getName() {
        return name;
    }
}

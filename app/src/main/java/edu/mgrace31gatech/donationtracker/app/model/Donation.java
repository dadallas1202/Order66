package edu.mgrace31gatech.donationtracker.app.model;

public class Donation {
    private String name;
    private static int id;

    public Donation(String name) {
        id++;
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }
}

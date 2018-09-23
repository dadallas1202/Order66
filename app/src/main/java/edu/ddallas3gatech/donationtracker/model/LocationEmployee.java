package edu.ddallas3gatech.donationtracker.model;

import java.util.Collection;

public class LocationEmployee extends User {
    private String username;
    private String password;
    private Location place;

    public LocationEmployee(String username, String password) {
        super(username, password, place);
    }

    @Override
    public void viewData(Collection<T> data) {
    }

    public void updateData(T data, Location place) {
    }
}
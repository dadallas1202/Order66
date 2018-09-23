package edu.ddallas3gatech.donationtracker.model;

import java.util.Collection;

public class Manager extends LocationEmployee {
    private String username;
    private String password;
    private Location place;

    public Manager(String username, String password) {
        super(username, password, place);
    }

    @Override
    public void viewData(Collection<T> data, Location place) {
    }

    @Override
    public void updateData(T data, Location place) {
    }
}
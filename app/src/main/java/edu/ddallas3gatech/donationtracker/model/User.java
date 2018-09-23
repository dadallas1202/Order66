package edu.ddallas3gatech.donationtracker.model;

import java.util.Collection;

public class User {
    private String username;
    private String password;
    private Location place;

    public User(String username, String password, Location place) {
        this.username = username;
        this.password = password;
        this.place = place;
    }

    public void viewData(Collection<T> data) {
    }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public void setPassword(String p) { password = p; }

}
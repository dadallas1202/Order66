import java.util.Collection;

package edu.ddallas3gatech.donationtracker.model;

public class User {
    private String username;
    private String password;
    private Location place;

    public User (String username, String password, Location place) {
        this.username = username;
        this.password = password;
        this.place = place;
    }

    public void viewData(Collection<T> data) {
    }
}
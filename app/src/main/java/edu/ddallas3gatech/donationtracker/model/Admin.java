import java.util.Collection;

package edu.ddallas3gatech.donationtracker.model;

public class Admin extends User {
    private String username;
    private String password;
    private Location place;

    public Admin(String username, String password) {
        super(username, password, place);
    }

    @Override
    public void viewData(Collection<T> data) {
    }

    public void addUser(User person) {
    }

    public void removeUser(User person) {
    }

    public void addLocation(Location place) {
    }

    public void removeLocation(Location place) {
    }
}
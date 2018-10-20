package edu.mgrace31gatech.donationtracker.app.model;

public class Admin extends RegisteredUser {
    public Admin(String name, String userName, String password) {
        super(name, userName, password, true, false);
    }
}

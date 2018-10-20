package edu.mgrace31gatech.donationtracker.app.model;


public class User extends RegisteredUser {

    public User(String name, String userName, String password) {
        super(name, userName, password, false, false);
    }

}

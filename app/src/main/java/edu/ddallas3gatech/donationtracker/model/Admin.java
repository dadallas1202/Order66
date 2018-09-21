package edu.ddallas3gatech.donationtracker.model;

public class Admin extends User {
    private String username;
    private String password;

    public Admin(String username, String password) {
        super(username, password);
    }
}
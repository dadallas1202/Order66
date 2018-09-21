package edu.ddallas3gatech.donationtracker.model;

public class Manager extends User {
    private String username;
    private String password;

    public Manager(String username, String password) {
        super(username, password);
    }
}
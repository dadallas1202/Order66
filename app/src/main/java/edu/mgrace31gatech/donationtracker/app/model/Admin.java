package edu.mgrace31gatech.donationtracker.app.model;

public class Admin extends RegisteredUser {
    private String name;
    private String userName;
    private String password;
    public static int numAdmins;


    public Admin(String name, String userName, String password) {
        super(name, userName, password, true);
    }
}

package edu.mgrace31gatech.donationtracker.app.model;


public class User extends RegisteredUser {

    private String name;
    private String userName;
    private String password;
    private boolean isAdmin;
    public static int numUsers;

    public User(String name, String userName, String password) {
        super(name, userName, password, false);
    }

}

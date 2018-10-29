package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisteredUser {

    public static List<String> userTypes = Arrays.asList("User", "Admin");
    private static Map<String, String> users = new HashMap<>();
    public static List<RegisteredUser> myUsers = new ArrayList<>();

    private String name;
    private String userName;
    private String password;
    private boolean isAdmin;


    protected RegisteredUser(String name, String userName, String password, boolean isAdmin) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }


    public static Map<String, String> usersList() {
        return users;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

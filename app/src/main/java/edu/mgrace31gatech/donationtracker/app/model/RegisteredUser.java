package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RegisteredUser {

    public static List<String> userTypes = Arrays.asList("User", "Admin", "Location Employees");
    private static Map<String, String> users = new HashMap<>();
    public static List<RegisteredUser> myUsers = new ArrayList<>();

    private String name;
    private String userName;
    private String password;
    private boolean isAdmin;
    private boolean isLocationEmployee;

    protected RegisteredUser(String name, String userName, String password, boolean isAdmin, boolean isLocationEmployee) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isLocationEmployee = isLocationEmployee;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isLocationEmployee() {
        return isLocationEmployee;
    }

    public static Map<String, String> usersList() {
        return users;
    }
}

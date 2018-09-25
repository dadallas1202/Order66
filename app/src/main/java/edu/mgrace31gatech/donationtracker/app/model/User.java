package edu.mgrace31gatech.donationtracker.app.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    public static List<String> userTypes = Arrays.asList("User", "Admin");
    private static Map<String, String> users = new HashMap<>();
    private String name;
    private boolean isAdmin;

    public static Map<String, String> usersList () {
        return users;
    }



}

package edu.mgrace31gatech.donationtracker.app.model;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

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

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public static Map<String, String> getUsers() {
        return users;
    }

    public String getName() {
        return name;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public static void setMyUsers(List<RegisteredUser> list) {
        if (list == null) {
            myUsers = new ArrayList<>();
        }
         else {
            myUsers = list;
        }
    }

    public static List<RegisteredUser> getMyUsers() {
        return myUsers;
    }

    public static boolean addUser(String name, String username, String password, String userType) {
        RegisteredUser newUser = userType.equals("User")
                ? new User(name, username, password)
                : new Admin(name, username, password);
        if (!users.containsKey(username)) {
            myUsers.add(newUser);
            users.put(username, password);
            return true;
        }
        return false;
    }

    public static boolean addUser(RegisteredUser user, boolean isAdmin) {
        String userType;
        if (isAdmin) {
            userType = "Admin";
        } else {
            userType = "User";
        }
        return addUser(user.getName(), user.getUserName(), user.getPassword(), userType);
    }

}

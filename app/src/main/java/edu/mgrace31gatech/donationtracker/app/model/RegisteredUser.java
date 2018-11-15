package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisteredUser {

    public static final List<String> userTypes = Arrays.asList("User", "Admin");
    private static final Map<String, String> users = new HashMap<>();
    private static List<RegisteredUser> myUsers = new ArrayList<>();

    private final String name;
    private final String userName;
    private final String password;
    private final boolean isAdmin;


    RegisteredUser(String name, String userName, String password, boolean isAdmin) {
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
        return Collections.unmodifiableMap(users);
    }

    private String getName() {
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
        return Collections.unmodifiableList(myUsers);
    }

    public static boolean addUser(String name, String username, String password, String userType) {
        RegisteredUser newUser = "User".equals(userType)
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

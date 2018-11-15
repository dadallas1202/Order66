package edu.mgrace31gatech.donationtracker.app.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a RegisteredUser.
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 * Alayna Panlilio, Julia Tang
 */
public class RegisteredUser {

    public static final List<String> userTypes = Arrays.asList("User", "Admin");
    private static final Map<String, String> users = new HashMap<>();
    private static List<RegisteredUser> myUsers = new ArrayList<>();

    private final String name;
    private final String userName;
    private final String password;
    private final boolean isAdmin;

    /**
     * Creates a registered user.
     * @param name The name of the registered user.
     * @param userName The Registered User's username for the account.
     * @param password The Registered User's password for the account.
     * @param isAdmin Whether the registered user is an admin or not.
     */
    RegisteredUser(String name, String userName, String password, boolean isAdmin) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    /**
     * Gets the username of the registered user.
     * @return The username of the registered user.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets the password of the registered user.
     * @return The password of the registered user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the users of the app.
     * @return Map of the users.
     */
    public static Map<String, String> getUsers() {
        return Collections.unmodifiableMap(users);
    }

    /**
     * Gets the name of the registered user.
     * @return The name of the registered user.
     */
    private String getName() {
        return name;
    }

    /**
     * Gets whether registered user is an admin or not.
     * @return true or false whether user is an admin or not.
     */
    public boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * Sets the list of registered users.
     * @param list The list of the users to set.
     */
    public static void setMyUsers(List<RegisteredUser> list) {
        if (list == null) {
            myUsers = new ArrayList<>();
        }
         else {
            myUsers = list;
        }
    }

    /**
     * Gets the list of registered users.
     * @return The list of registered users.
     */
    public static List<RegisteredUser> getMyUsers() {
        return Collections.unmodifiableList(myUsers);
    }

    /**
     * Adds a users to the list of registered users.
     * @param name The name of the user.
     * @param username The User's username for the account.
     * @param password The User's password for the account.
     * @param userType The type of user.
     * @return Whether the users was added or not.
     */
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

    /**
     * Adds the specific user.
     * @param user The user to be added.
     * @param isAdmin Whether the user is an admin or not.
     * @return Whether the user was added or not.
     */
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

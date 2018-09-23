package edu.ddallas3gatech.donationtracker.model;

import android.support.annotation.NonNull;
import android.support.compat.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final Model instance = new Model();
    public static Model getInstance() {
        return instance;
    }

    private List<User> users;

    private User currentUser;

    private final User theNullUser =
            new User("No such username", "No such password,",
                    new Location("Georgia", "Atlanta"));

    private Model() {
        users = new ArrayList<>();

        loadDummyData();
    }

    private void loadDummyData() {
        users.add(new User("user", "pass", new Location("Georgia", "Atlanta")));
    }

    public List<User> getUsers() { return users; }

    public boolean addUser(User user) {
        for (User u : users) {
            if (u.equals(user))
                return false;
        }
        users.add(user);
        return true;
    }

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User user) { currentUser = user; }

    public User getUserByUsername(String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return theNullUser;
    }

    public void replaceUserPassword(User user) {
        User existing = getUserByUsername(user.getUsername());

        if (BuildConfig.DEBUG && (existing == null)) { throw new AssertionError(); }

        existing.setPassword(user.getPassword());
    }




}

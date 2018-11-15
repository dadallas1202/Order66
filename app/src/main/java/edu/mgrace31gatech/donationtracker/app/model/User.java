package edu.mgrace31gatech.donationtracker.app.model;

/**
 * Represents a User.
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace, Alayna Panlilio, Julia Tang
 */
public class User extends RegisteredUser {
    /**
     * Creates a user.
     * @param name The name of the user.
     * @param userName The User's username for the account.
     * @param password The User's password for the account.
     */
    public User(String name, String userName, String password) {
        super(name, userName, password, false);
    }

}

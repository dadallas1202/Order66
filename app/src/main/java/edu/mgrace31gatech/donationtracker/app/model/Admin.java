package edu.mgrace31gatech.donationtracker.app.model;

/**
 * Represents an Admin.
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 * Alayna Panlilio, Julia Tang
 */
public class Admin extends RegisteredUser {
    /**
     * Creates an Admin with the specified name, username, and password.
     * @param name The Admin's name.
     * @param userName The Admin's username for the account.
     * @param password The Admin's password for the account.
     */
    public Admin(String name, String userName, String password) {
        super(name, userName, password, true);
    }
}

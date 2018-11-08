package edu.mgrace31gatech.donationtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import edu.mgrace31gatech.donationtracker.app.model.Admin;
import edu.mgrace31gatech.donationtracker.app.model.RegisteredUser;
import edu.mgrace31gatech.donationtracker.app.model.User;

import static org.junit.Assert.assertTrue;

public class DonationTrackerTest {

    private List<RegisteredUser> myUsers;

    @Before
    public void setUp() {
        myUsers = new ArrayList<>();
        myUsers.add(new User("Marciana Ross", "ross", "rossy"));
        myUsers.add(new Admin("Charlie Horse", "horse", "pony"));
        myUsers.add(new User("Marciana Ross", "ross", "rossy"));
        myUsers.add(new Admin("Charlie Horse", "horse", "pony"));
    }

    @Test
    public void testAddUser() {
        assertTrue("User was not added to the user list",
                RegisteredUser.addUser(myUsers.get(0), myUsers.get(0).getIsAdmin()));
        assertTrue("User was not added to the user list",
                RegisteredUser.addUser(myUsers.get(1), myUsers.get(1).getIsAdmin()));
        assertTrue("User was added to the user when not supposed to be added",
                !RegisteredUser.addUser(myUsers.get(2), myUsers.get(2).getIsAdmin()));
        assertTrue("User was added to the user when not supposed to be added",
                !RegisteredUser.addUser(myUsers.get(3), myUsers.get(3).getIsAdmin()));
    }
}

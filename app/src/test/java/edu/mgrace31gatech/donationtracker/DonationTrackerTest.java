package edu.mgrace31gatech.donationtracker;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import edu.mgrace31gatech.donationtracker.app.model.Admin;
import edu.mgrace31gatech.donationtracker.app.model.Donation;
import edu.mgrace31gatech.donationtracker.app.model.DonationModel;
import edu.mgrace31gatech.donationtracker.app.model.RegisteredUser;
import edu.mgrace31gatech.donationtracker.app.model.User;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class DonationTrackerTest {

    private List<RegisteredUser> myUsers;
    private DonationModel testModel;

    @Before
    public void setUp() {
        myUsers = new ArrayList<>();
        myUsers.add(new User("Marciana Ross", "ross", "rossy"));
        myUsers.add(new Admin("Charlie Horse", "horse", "pony"));
        myUsers.add(new User("Marciana Ross", "ross", "rossy"));
        myUsers.add(new Admin("Charlie Horse", "horse", "pony"));

        testModel = new DonationModel();
        testModel.addDonation(new Donation("Shirt", "Blue Shirt",
                "Blue Striped Shirt", 20, "Clothes", "",
                null, null, 1));
        testModel.addDonation(new Donation("Rug", "furry rug",
                "square furry rug", 35, "Household", "",
                LocalTime.now(), LocalDate.now(), 2));
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

    @Test
    public void testFindDonationById() {
        assertEquals("Returns incorrect donation", testModel.findDonationById(1),
                testModel.getDonation(0));
        assertEquals("Returns incorrect donation", testModel.findDonationById(2),
                testModel.getDonation(1));
        assertNotNull("No donation with this id", testModel.findDonationById(2));
    }
}

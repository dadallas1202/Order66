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
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;
import edu.mgrace31gatech.donationtracker.app.model.RegisteredUser;
import edu.mgrace31gatech.donationtracker.app.model.User;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DonationTrackerTest {

    private List<RegisteredUser> myUsers;
    private List<Location> myLocations;
    private DonationModel testDonationModel;
    @Before
    public void setUp() {
        myUsers = new ArrayList<>();
        myUsers.add(new User("Marciana Ross", "ross", "rossy"));
        myUsers.add(new Admin("Charlie Horse", "horse", "pony"));
        myUsers.add(new User("Marciana Ross", "ross", "rossy"));
        myUsers.add(new Admin("Charlie Horse", "horse", "pony"));

        myLocations = new ArrayList<>();
        myLocations.add(new Location(1, "Boys and Girls Club", "33.73182", "-84.43971",
                "1642 RICHLAND RD", "ATLANTA", "GEORGIA", "30332", "Store", "(404) 555 - 1234", "www.bgc.wool.ga"));
        myLocations.add(new Location(2, "D&D CONVENIENCE STORE","33.71747" ,"-84.2521",
                "2426 COLUMBIA DRIVE","DECATUR","GA","30034","Drop Off","(404) 555 - 9876","www.ddconv.com"));
        myLocations.add(new Location(3,"KEEP NORTH FULTON BEAUTIFUL","33.96921","-84.3688",
                "470 MORGAN FALLS RD","Sandy Springs","GA","30302","Store","(770) 555 - 7321","www.knfb.org"));

        testDonationModel = new DonationModel();
        testDonationModel.addDonation(new Donation("Shirt", "Blue Shirt",
                "Blue Striped Shirt", 20, "Clothes", "",
                null, null, 1));
        testDonationModel.addDonation(new Donation("Rug", "furry rug",
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
    public void testFindLocationByKey() {
        LocationsModel locations = LocationsModel.INSTANCE;
        locations.setItems(myLocations);
        assertEquals("Location found in the list.", myLocations.get(0), locations
                .findLocationByKey(myLocations.get(0).getKey()));
        assertEquals("Location found in the list.", myLocations.get(1), locations
                .findLocationByKey(myLocations.get(1).getKey()));
        assertEquals("Location found in the list.", myLocations.get(2), locations
                .findLocationByKey(myLocations.get(2).getKey()));
        assertNull("Location cannot be found in the list.", locations.findLocationByKey(76));
        assertNull("Location cannot be found in the list.", locations.findLocationByKey(19));
    }

    @Test
    public void testFindDonationById() {
        assertEquals("Incorrect donation found", testDonationModel.findDonationById(1),
                testDonationModel.getDonation(0));
        assertEquals("Incorrect donation found", testDonationModel.findDonationById(2),
                testDonationModel.getDonation(1));
        assertNotNull("No donation with this id", testDonationModel.findDonationById(2));
    }
}

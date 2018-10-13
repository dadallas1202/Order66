package edu.mgrace31gatech.donationtracker.app.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LocationsModel {

        public static final LocationsModel INSTANCE = new LocationsModel();

        private List<Location> locationsList;

        private LocationsModel() {
            locationsList = new ArrayList<>();
        }

        public void addLocation(Location location) {
            locationsList.add(location);
        }

        public List<Location> getItems() {
            return locationsList;
        }

        public Location findLocationByKey(int key) {
            for (Location l : locationsList) {
                if (l.getKey().equals(key)) return l;
            }
            Log.d("MYAPP", "Warning - Failed to find id: " + key);
            return null;
        }

}

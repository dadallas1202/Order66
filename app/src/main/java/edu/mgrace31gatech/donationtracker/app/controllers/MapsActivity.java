package edu.mgrace31gatech.donationtracker.app.controllers;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * Activity that displays a Google Map of all locations, with pins at each location, displaying name
 * and phone number for the location.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 *                                   Alayna Panlilio, Julia Tang
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Reference to controller interface in the model
        final LocationsModel locationService = LocationsModel.getInstance();

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                //Creating a new marker
                MarkerOptions markerOptions = new MarkerOptions();

                //Setting the position for the marker
                markerOptions.position(latLng);
            }
        });

        //Get data for display
        List<Location> locationList = locationService.getItems();
        Log.d("MYAPP", "Going to add pins");
        //Add a pin for each location in locationList
        for (Location l: locationList) {
            LatLng loc = new LatLng(Double.parseDouble(l.getLatitude()),
                    Double.parseDouble(l.getLongitude()));
            mMap.addMarker(
                    new MarkerOptions().position(loc).title(l.getName()).snippet(l.getPhone()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        }
    }
}

package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Location;
import edu.mgrace31gatech.donationtracker.app.model.LocationsModel;

public class HomePageActivity extends AppCompatActivity {

    private Button Logout;
    private Button Locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Logout= (Button)findViewById(R.id.logOutButton);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePageActivity.this, WelcomePageActivity.class);
                startActivity(intent);
            }
        });

        Locations = (Button)findViewById(R.id.locationButton);
        Locations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readSDFile();
                Intent intent = new Intent(HomePageActivity.this, LocationList.class);
                startActivity(intent);
            }
        });
    }
    private void readSDFile () {
        LocationsModel model = LocationsModel.INSTANCE;

        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata1);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                String key= tokens[0];
                String name = tokens[1];
                String lat = tokens[2];
                String lon = tokens[3];
                String add = tokens[4];
                String city = tokens[5];
                String state = tokens[6];
                String zip = tokens[7];
                String type = tokens[8];
                String phone = tokens[9];
                String web = tokens[10];

                model.addLocation(new Location(key,name, lat, lon, add, city, state, zip, type, phone, web));
            }
            br.close();
        } catch (IOException e) {

        }
    }
}

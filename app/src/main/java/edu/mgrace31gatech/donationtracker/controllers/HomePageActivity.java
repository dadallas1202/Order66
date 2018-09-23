package edu.mgrace31gatech.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.mgrace31gatech.donationtracker.R;

public class HomePageActivity extends AppCompatActivity {

    private Button Logout;

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
    }
}

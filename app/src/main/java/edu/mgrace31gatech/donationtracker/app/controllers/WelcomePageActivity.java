package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.mgrace31gatech.donationtracker.R;

public class WelcomePageActivity extends AppCompatActivity {

    private Button Login;
    private Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        Login= (Button)findViewById(R.id.welcomeloginButton);
        Register = (Button) findViewById(R.id.registerButton);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomePageActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomePageActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}

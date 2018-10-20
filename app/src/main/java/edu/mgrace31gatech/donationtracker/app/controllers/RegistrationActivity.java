package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Admin;
import edu.mgrace31gatech.donationtracker.app.model.LocationEmployee;
import edu.mgrace31gatech.donationtracker.app.model.Model;
import edu.mgrace31gatech.donationtracker.app.model.RegisteredUser;
import edu.mgrace31gatech.donationtracker.app.model.User;

public class RegistrationActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Username;
    private EditText Password;
    private Button Register;
    private Button Cancel;
    private Spinner UserType;
    private EditText Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Name = (EditText)findViewById(R.id.nameBox);
        Username = (EditText)findViewById(R.id.userNameBox);
        Password = (EditText)findViewById(R.id.passWordBox);
        Register = (Button)findViewById(R.id.registerButton);
        Cancel = (Button)findViewById(R.id.cancelbutton);
        UserType = (Spinner)findViewById(R.id.user_type_spinner);
        Location = (EditText) findViewById(R.id.locationBox);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, RegisteredUser.userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserType.setAdapter(adapter);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisteredUser.usersList().put(Username.getText().toString(), Password.getText().toString());
                RegisteredUser newUser;
                if (UserType.getSelectedItem().equals("User")) {
                    newUser = new User(Name.getText().toString(), Username.getText().toString(), Password.getText().toString());
                } else if (UserType.getSelectedItem().equals("Admin")) {
                    newUser = new Admin(Name.getText().toString(), Username.getText().toString(), Password.getText().toString());
                } else {
                    newUser = new LocationEmployee(Name.getText().toString(), Username.getText().toString(), Password.getText().toString(), Location.getText().toString());
                }
                RegisteredUser.myUsers.add(newUser);
                Intent intent = new Intent(RegistrationActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, WelcomePageActivity.class);
                startActivity(intent);
            }
        });
    }

}

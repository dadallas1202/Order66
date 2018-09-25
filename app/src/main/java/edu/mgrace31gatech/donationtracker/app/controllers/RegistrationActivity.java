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
import edu.mgrace31gatech.donationtracker.app.model.Model;
import edu.mgrace31gatech.donationtracker.app.model.User;

public class RegistrationActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Register;
    private Button Cancel;
    private Spinner UserType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Username = (EditText)findViewById(R.id.userNameBox);
        Password = (EditText)findViewById(R.id.passWordBox);
        Register = (Button)findViewById(R.id.registerButton);
        Cancel = (Button)findViewById(R.id.cancelbutton);
        UserType = (Spinner)findViewById(R.id.user_type_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserType.setAdapter(adapter);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.usersList().put(Username.getText().toString(), Password.getText().toString());
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

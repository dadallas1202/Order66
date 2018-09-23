package edu.mgrace31gatech.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.mgrace31gatech.donationtracker.R;

public class LoginActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private TextView BadAttempt;
    private Button Login;
    private Button Cancel;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = (EditText)findViewById(R.id.userNameBox);
        Password = (EditText)findViewById(R.id.passWordBox);
        BadAttempt = (TextView)findViewById(R.id.badLoginAttempt);
        Login = (Button)findViewById(R.id.logInbutton);
        Cancel = (Button)findViewById(R.id.cancelbutton);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Username.getText().toString(), Password.getText().toString());
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WelcomePageActivity.class);
                startActivity(intent);
            }
    });
    }

    private void validate(String userName, String userPassword) {
        if((userName.equals("user")) && (userPassword.equals("pass"))) {
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(intent);
        } else{
            counter++;
            BadAttempt.setText("Number of incorrect attempts: " + String.valueOf(counter));
        }
    }
}

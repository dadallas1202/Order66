package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.RegisteredUser;
import edu.mgrace31gatech.donationtracker.app.model.User;

/**
 * Activity that allows for a user to login.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 *                                   Alayna Panlilio, Julia Tang
 */
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
        Login = (Button)findViewById(R.id.loginButton);
        Cancel = (Button)findViewById(R.id.cancelButton);

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
        Map<String, String> users = RegisteredUser.getUsers();
        if(users.containsKey(userName) && users.get(userName).equals(userPassword)) {
            Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(intent);
        } else{
            counter++;
            String correct = "Number of incorrect attempts: " + String.valueOf(counter);
            BadAttempt.setText(correct);
            if (counter >= 3) {
                Login.setEnabled(false);
            }
        }
    }

    private List<RegisteredUser> getList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        TypeToken<List<RegisteredUser>> typeToken = new TypeToken<List<RegisteredUser>>(){};
        Type type = typeToken.getType();
        return gson.fromJson(json, type);
    }
}

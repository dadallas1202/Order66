package edu.mgrace31gatech.donationtracker.app.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.RegisteredUser;

/**
 * Activity that allows for a user to register on the app.
 *
 * @author Team: Order 66; Members: Kierra Brigman, Andrew Dallas, Marie Grace,
 *                                   Alayna Panlilio, Julia Tang
 */
public class RegistrationActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Username;
    private EditText Password;
    private Spinner UserType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Name = findViewById(R.id.nameBox);
        Username = findViewById(R.id.userNameBox);
        Password = findViewById(R.id.passWordBox);
        Button register = findViewById(R.id.registerButton);
        Button cancel = findViewById(R.id.cancelbutton);
        UserType = findViewById(R.id.user_type_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(
                this, android.R.layout.simple_spinner_item, RegisteredUser.userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserType.setAdapter(adapter);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisteredUser.setMyUsers(getList("users"));
                RegisteredUser.addUser(Name.getText().toString(), Username.getText().toString(),
                        Password.getText().toString(), UserType.getSelectedItem().toString());
                saveList(RegisteredUser.getMyUsers(), "users");
                Intent intent = new Intent(RegistrationActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, WelcomePageActivity.class);
                startActivity(intent);
            }
        });
    }
    private void saveList(List<RegisteredUser> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    private List<RegisteredUser> getList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<List<RegisteredUser>>(){}.getType();
        return gson.fromJson(json, type);
    }
}

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mgrace31gatech.donationtracker.R;
import edu.mgrace31gatech.donationtracker.app.model.Admin;
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

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, RegisteredUser.userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserType.setAdapter(adapter);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisteredUser.setMyUsers(getList("users"));
                RegisteredUser.addUser(Name.getText().toString(), Username.getText().toString(),
                        Password.getText().toString(), UserType.getSelectedItem().toString());
//                RegisteredUser newUser = UserType.getSelectedItem().equals("User")
//                        ? new User(Name.getText().toString(), Username.getText().toString(), Password.getText().toString())
//                        : new Admin(Name.getText().toString(), Username.getText().toString(), Password.getText().toString());
//                if (!RegisteredUser.myUsers.contains(newUser)) {
//                    RegisteredUser.myUsers.add(newUser);
//                    RegisteredUser.getUsers().put(Username.getText().toString(), Password.getText().toString());
//                }
                saveList(RegisteredUser.getMyUsers(), "users");
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
    public void saveList(List<RegisteredUser> list, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();     // This line is IMPORTANT !!!
    }

    public List<RegisteredUser> getList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<List<RegisteredUser>>() {}.getType();
        return gson.fromJson(json, type);
    }
}

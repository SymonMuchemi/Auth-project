package com.example.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class home extends AppCompatActivity {

    TextView welcomeText;
    FirebaseAuth auth;
    Button logoutBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeText = findViewById(R.id.successMessage);
        auth = FirebaseAuth.getInstance();
        logoutBtn = findViewById(R.id.logOut);

        // get email address from login activity
        String email = getIntent().getStringExtra("email");

        // concatenate the welcome text with the user's email address
        welcomeText.setText("Your email address is: " + email);

        logoutBtn.setOnClickListener(view -> {
            // sign out the user
            FirebaseAuth.getInstance().signOut();
            // finish the activity
            finish();
        });
    }
}
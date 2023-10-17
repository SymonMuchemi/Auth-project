package com.example.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Login extends AppCompatActivity {
    TextInputEditText email, password;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind the TextInputEditTexts and Button to their respective views
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_button);

        loginBtn.setOnClickListener(v -> {
            // Get the text from the TextInputEditTexts
            String emailText = Objects.requireNonNull(email.getText()).toString();
            String passwordText = Objects.requireNonNull(password.getText()).toString();
        });
    }
}
package com.example.authapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {
    TextInputEditText email, password;
    Button loginBtn;
    TextView textView;
    ProgressBar progressBar;
    FirebaseAuth mAuth;

    // check if user is already logged in
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            // start the home activity if user is already logged in
            Intent intent = new Intent(this, home.class);

            // pass the user's email address to the home activity
            intent.putExtra("email", currentUser.getEmail());

            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Bind the TextInputEditTexts and Button to their respective views
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_button);
        textView = findViewById(R.id.noAccountText);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        textView.setOnClickListener(v -> {
            // create an intent to start the activity_reg activity
            Intent intent = new Intent(Login.this, Reg.class);
            // start the intent
            startActivity(intent);
        });

        loginBtn.setOnClickListener( view -> {
            progressBar.setVisibility(View.VISIBLE);
            // Get the text from the TextInputEditTexts
            String emailText = String.valueOf(email.getText());
            String passwordText = String.valueOf(password.getText());

            if (emailText.isEmpty()) {
                Toast.makeText(this,
                            "Enter email address",
                                Toast.LENGTH_SHORT).show();
                return;
            }
            else if (passwordText.isEmpty()) {
                Toast.makeText(this,
                                "Enter password",
                                    Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this, task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(this,
                                            "Login successful",
                                                Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this, home.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(this,
                                            Objects.requireNonNull(task.getException()).getMessage(),
                                                Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}
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

public class Reg extends AppCompatActivity {
    TextInputEditText email, password, passwordConfirm;
    Button regBtn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;

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
        setContentView(R.layout.activity_reg);

        // Bind the TextInputEditTexts and Button to their respective views
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        regBtn = findViewById(R.id.reg_button);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);

        mAuth = FirebaseAuth.getInstance();

        textView.setOnClickListener(v -> {
            // create an intent to start the activity_reg activity
            Intent intent = new Intent(Reg.this, Login.class);
            // start the intent
            startActivity(intent);
        });

        regBtn.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            // Get the text from the TextInputEditTexts
            String emailText = String.valueOf(email.getText());
            String passwordText = String.valueOf(password.getText());

            if (emailText.isEmpty()) {
                Toast.makeText(this, "Enter email address", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (passwordText.isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                    .addOnCompleteListener(this, task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "Account created successful", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    });
    });
    }
}
package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;


public class SignUp extends AppCompatActivity {

    private EditText edtNameSign;
    private EditText edtEmailSign;
    private EditText edtPassSign;
    private EditText edtPassRepeatSign;
    private Button btSignUp;
    private SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtNameSign = findViewById(R.id.edtNameSign);
        edtEmailSign = findViewById(R.id.edtEmailSign);
        edtPassSign = findViewById(R.id.edtPassSign);
        edtPassRepeatSign = findViewById(R.id.edtPassRepeatSign);
        btSignUp = findViewById(R.id.btSignin);

        sharedPreferences = getSharedPreferences("SignUpData", MODE_PRIVATE);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        // Restore user input if available
        String savedName = sharedPreferences.getString("name", "");
        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");
        String savedRepeatPassword = sharedPreferences.getString("repeat_password", "");

        edtNameSign.setText(savedName);
        edtEmailSign.setText(savedEmail);
        edtPassSign.setText(savedPassword);
        edtPassRepeatSign.setText(savedRepeatPassword);
    }

    private void signUp() {
        String name = edtNameSign.getText().toString().trim();
        String email = edtEmailSign.getText().toString().trim();
        String password = edtPassSign.getText().toString().trim();
        String repeatPassword = edtPassRepeatSign.getText().toString().trim();

        // Perform validation and sign up process

        // Save user input
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("repeat_password", repeatPassword);
        editor.apply();

        // Continue with sign up process
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Save user input before destroying the activity
        String name = edtNameSign.getText().toString().trim();
        String email = edtEmailSign.getText().toString().trim();
        String password = edtPassSign.getText().toString().trim();
        String repeatPassword = edtPassRepeatSign.getText().toString().trim();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("repeat_password", repeatPassword);
        editor.apply();
    }
}

package com.example.mainpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class LogIn extends AppCompatActivity {

    private EditText edtEmail;
    private TextView textView;
    private EditText edtPassword;
    private Button login;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        login = findViewById(R.id.btLogin);
        textView = findViewById(R.id.textsw);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, SignUp.class);
                startActivity(intent);
            }
        });

        sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });

        // Restore user input if available
        String savedEmail = sharedPreferences.getString("email", "");
        String savedPassword = sharedPreferences.getString("password", "");
        edtEmail.setText(savedEmail);
        edtPassword.setText(savedPassword);
    }

    private void signInUser() {
        CollectionReference collectionRef = FirebaseFirestore.getInstance().collection("SignUp_page");
        collectionRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot querySnapshot) {
                for (DocumentSnapshot documentSnapshot : querySnapshot) {
                    String documentId = documentSnapshot.getId();

                    String email = documentSnapshot.getString("email");
                    String password = documentSnapshot.getString("pass");

                    if (edtEmail.getText().toString().equals(email)) {
                        if (edtPassword.getText().toString().equals(password)) {
                            // Save user input
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.apply();

                            Intent intent = new Intent(LogIn.this, MainActivity.class);
                            intent.putExtra("docID", documentId);
                            startActivity(intent);
                            return;
                        }
                    }
                }

                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(LogIn.this);
                dlgAlert.setMessage("Invalid email or password");
                dlgAlert.setTitle("Error Message...");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Save user input before destroying the activity
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }
}

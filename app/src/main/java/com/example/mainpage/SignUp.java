package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    private static final String KEY_NAME = "key_name";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_PASSWORD = "key_password";
    private static final String KEY_REPEAT_PASSWORD = "key_repeat_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtNameSign = findViewById(R.id.edtNameSign);
        edtEmailSign = findViewById(R.id.edtEmailSign);
        edtPassSign = findViewById(R.id.edtPassSign);
        edtPassRepeatSign = findViewById(R.id.edtPassRepeatSign);
        btSignUp = findViewById(R.id.btSignin);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNameSign.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "You did not enter a username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edtEmailSign.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "You did not enter a email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edtPassSign.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "You did not enter a password", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edtPassSign.getText().toString().length() <= 8) {
                    Toast.makeText(SignUp.this, "your password must be at least 8 char long", Toast.LENGTH_SHORT).show();
                    return;

                }
                if(edtPassRepeatSign.getText().toString().equals("")){
                    Toast.makeText(SignUp.this, "Enter the repeat password", Toast.LENGTH_SHORT).show();
                    return;
                }else if(!edtPassRepeatSign.getText().toString().equals(edtPassSign.getText().toString())){
                    Toast.makeText(SignUp.this, "Enter the repeat password correctly", Toast.LENGTH_SHORT).show();
                    return;
                }

                database.collection("SignUp_page")
                        .add(new Member(edtNameSign.getText().toString(), edtEmailSign.getText().toString(), edtPassSign.getText().toString()));
                // Start the new activity
                Intent intent = new Intent(SignUp.this, LogIn.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Restore the entered data if it was saved before
        restoreData();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Save the entered data when the activity is about to be destroyed
        saveData();
    }

    private void restoreData() {
        edtNameSign.setText(getSavedData(KEY_NAME));
        edtEmailSign.setText(getSavedData(KEY_EMAIL));
        edtPassSign.setText(getSavedData(KEY_PASSWORD));
        edtPassRepeatSign.setText(getSavedData(KEY_REPEAT_PASSWORD));
    }

    private void saveData() {
        saveInputData(KEY_NAME, edtNameSign.getText().toString());
        saveInputData(KEY_EMAIL, edtEmailSign.getText().toString());
        saveInputData(KEY_PASSWORD, edtPassSign.getText().toString());
        saveInputData(KEY_REPEAT_PASSWORD, edtPassRepeatSign.getText().toString());
    }

    private void saveInputData(String key, String value) {
        getPreferences(MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply();
    }

    private String getSavedData(String key) {
        return getPreferences(MODE_PRIVATE).getString(key, "");
    }
}

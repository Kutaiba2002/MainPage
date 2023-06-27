package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText edtNameSign;
    private EditText edtEmailSign;
    private EditText edtPassSign;
    private EditText edtPassRepeatSign;
    private Button btSignUp;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtNameSign = findViewById(R.id.edtNameSign);
        edtEmailSign = findViewById(R.id.edtEmailSign);
        edtPassSign = findViewById(R.id.edtPassSign);
        edtPassRepeatSign = findViewById(R.id.edtPassRepeatSign);
        btSignUp = findViewById(R.id.btSignUp);

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
            }
        });

    }
}
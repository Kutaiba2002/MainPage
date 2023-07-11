package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private EditText edtField1;
    private EditText edtField2;
    private String fieldEmail;
    private String fieldName;
    private String collectionName;
    private String documentId;
    private FirebaseFirestore db;
    private DocumentReference docRef;
    private SharedPreferences sharedPreferencesPastData;
    private SharedPreferences.Editor editorPastData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtField1 = findViewById(R.id.edtUserNameUpdaet);
        edtField2 = findViewById(R.id.edtEmailUpdaet);
        Intent intent = getIntent();
        String id = intent.getStringExtra("docID");

        db = FirebaseFirestore.getInstance();

        collectionName = "SignUp_page";
        documentId = id;

        Button updateButton = findViewById(R.id.btUpdaet);
        docRef = db.collection(collectionName).document(documentId);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                              @Override
                                              public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                  if (documentSnapshot.exists()) {
                                                      // Document exists, retrieve its data
                                                       fieldEmail = documentSnapshot.getString("email");
                                                       fieldName = documentSnapshot.getString("name");

                                                      // Set the retrieved data to the EditText fields
                                                      edtField1.setText(fieldEmail);
                                                      edtField2.setText(fieldName);

                                                      sharedPreferencesPastData = getSharedPreferences("pastData", Context.MODE_PRIVATE);

                                                      editorPastData = sharedPreferencesPastData.edit();

                                                      String email = edtField1.getText().toString();
                                                      String name = edtField2.getText().toString();
                                                      editorPastData.putString("email", email);
                                                      editorPastData.putString("name", name);
                                                      editorPastData.apply();
                                                  }
                                              }
                                          });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("email", edtField1.getText().toString());
                            updates.put("name", edtField2.getText().toString());

                            SharedPreferences sharedPreferencesnewData = getSharedPreferences("newData", Context.MODE_PRIVATE);

                            SharedPreferences.Editor editorNewData = sharedPreferencesnewData.edit();

                            String email = edtField1.getText().toString();
                            String name = edtField2.getText().toString();
                            editorNewData.putString("email", email);
                            editorNewData.putString("name", name);
                            editorNewData.apply();

                            String password = documentSnapshot.getString("pass");
                            updates.put("pass", password);
                            docRef.set(updates)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            SharedPreferences sharedPreferencesPastData = getSharedPreferences("pastData", Context.MODE_PRIVATE);
                                            String pastData = sharedPreferencesPastData.getString("email", "") + " - " + sharedPreferencesPastData.getString("name", "");
                                            String newData = sharedPreferencesnewData.getString("email", "") + " - " + sharedPreferencesnewData.getString("name", "");
                                            Toast.makeText(ProfileActivity.this, pastData + " Updated Successfully To " + newData, Toast.LENGTH_SHORT).show();
                                            Intent intent1 = new Intent(ProfileActivity.this, MainActivity.class);
                                            startActivity(intent1);
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            // Error occurred while updating the document
                                            Log.e("TAG", "Error updating document: " + e.getMessage());
                                        }
                                    });
                        }
                    }
                });
            }
        });


    }
}
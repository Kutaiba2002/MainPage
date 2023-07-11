package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class ShowDetails extends AppCompatActivity {

    private TextView text1;
    private ImageView image;
    private TextView text2;
    private TextView text3;
    private Button button;

    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    private String name1;
    private String email;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        Intent intent = getIntent();
        String name = intent.getExtras().getString("name");
        int id = intent.getIntExtra("image", 0);
        int price = intent.getIntExtra("price", 0);
        String description = intent.getStringExtra("description");
        String docId = intent.getStringExtra("docId");

        String collectionName = "SignUp_page";
        DocumentReference docRef = database.collection(collectionName).document(docId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Document exists, retrieve its attributes
                    name1 = documentSnapshot.getString("name");
                    email = documentSnapshot.getString("email");
                    // Use the retrieved name and email as needed in your activity
                    Log.d("TAG", "Name: " + name1 + ", Email: " + email);
                } else {
                    // Document does not exist
                    Log.d("TAG", "Document does not exist");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Error occurred while fetching the document
                Log.e("TAG", "Error getting document: " + e.getMessage());
            }
        });
 //       int person = intent.getIntExtra("person", 0);

        text1 = findViewById(R.id.nameTxt);
        image = findViewById(R.id.image);
        text2 = findViewById(R.id.textPrice);
        text3 = findViewById(R.id.textDescription);
        button = findViewById(R.id.button);

        text1.setText(name);
        image.setImageResource(id);
        text2.setText(String.valueOf(price)+"$/day");
        text3.setText(description);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showReservationFormDialog();
            }
        });
    }

    private void showReservationFormDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_reservation_form, null);
        builder.setView(dialogView);

        DatePicker startDatePicker = dialogView.findViewById(R.id.startDatePicker);
        DatePicker endDatePicker = dialogView.findViewById(R.id.endDatePicker);

        builder.setPositiveButton("Reserve", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int startDay = startDatePicker.getDayOfMonth();
                int startMonth = startDatePicker.getMonth() + 1;
                int startYear = startDatePicker.getYear();
                String startDate = startDay + "/" + startMonth + "/" + startYear;

                int endDay = endDatePicker.getDayOfMonth();
                int endMonth = endDatePicker.getMonth() + 1;
                int endYear = endDatePicker.getYear();
                String endDate = endDay + "/" + endMonth + "/" + endYear;

                performReservation(startDate, endDate);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void performReservation(String startDate, String endDate) {
        checkRoomAvailability(String.valueOf(text1.getText()), startDate, endDate);



            Reservation reservation = new Reservation(String.valueOf(text1.getText()), name1, email, startDate, endDate);

            // Store the reservation in Firestore
            FirebaseFirestore.getInstance().collection("Reserve")
                    .add(reservation)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(ShowDetails.this, "Reserved Successfully.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ShowDetails.this, "Failed to reserve.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    private void checkRoomAvailability(String roomName, String startDate, String endDate) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Query for reservations with an end date greater than or equal to the given start date
        Query query1 = db.collection("Reserve")
                .whereEqualTo("roomName", roomName)
                .whereGreaterThanOrEqualTo("endDate", startDate);

        // Query for reservations with a start date less than or equal to the given end date
        Query query2 = db.collection("Reserve")
                .whereEqualTo("roomName", roomName)
                .whereLessThanOrEqualTo("startDate", endDate);

        // Execute the first query
        query1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    if (querySnapshot != null && !querySnapshot.isEmpty()) {
                        // The room is already reserved within the given date range
                        Toast.makeText(ShowDetails.this, "Room is already reserved for the selected dates.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        // Execute the second query
                        query2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    QuerySnapshot querySnapshot = task.getResult();
                                    if (querySnapshot != null && !querySnapshot.isEmpty()) {
                                        // The room is already reserved within the given date range
                                        Toast.makeText(ShowDetails.this, "Room is already reserved for the selected dates.", Toast.LENGTH_SHORT).show();
                                        return;
                                    } else {
                                        // The room is available for reservation
                                        Toast.makeText(ShowDetails.this, "Room is available for reservation.", Toast.LENGTH_SHORT).show();
                                        performReservation(startDate, endDate);
                                    }
                                } else {
                                    Toast.makeText(ShowDetails.this, "Failed to check room availability.", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
                    }
                } else {
                    Toast.makeText(ShowDetails.this, "Failed to check room availability.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }


}
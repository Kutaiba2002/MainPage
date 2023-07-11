package com.example.mainpage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowDetails extends AppCompatActivity {

    private TextView text1;
    private ImageView image;
    private TextView text2;
    private TextView text3;
    private Button button;

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText dateEditText;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

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
 //       int person = intent.getIntExtra("person", 0);

        text1 = findViewById(R.id.nameTxt);
        image = findViewById(R.id.image);
        text2 = findViewById(R.id.textPrice);
        text3 = findViewById(R.id.textDescription);
        button = findViewById(R.id.button);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        dateEditText = findViewById(R.id.datePicker);

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

        EditText nameEditText = dialogView.findViewById(R.id.nameEditText);
        EditText emailEditText = dialogView.findViewById(R.id.emailEditText);
        DatePicker datePicker = dialogView.findViewById(R.id.datePicker);

        builder.setPositiveButton("Reserve", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1; // Month is zero-based, so add 1
                int year = datePicker.getYear();
                String date = day + "/" + month + "/" + year;

                performReservation(name, email, date);
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

    private void performReservation(String name, String email, String date) {
        Reservation reservation = new Reservation(name, email, date);

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


}
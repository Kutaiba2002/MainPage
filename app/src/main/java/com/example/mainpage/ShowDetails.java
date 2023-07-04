package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.FirebaseFirestore;

public class ShowDetails extends AppCompatActivity {

    private TextView text1;
    private ImageView image;
    private TextView text2;
    private TextView text3;
    private Button button;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    @SuppressLint("MissingInflatedId")
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

        text1.setText(name);
        image.setImageResource(id);
        text2.setText(String.valueOf(price)+"$/day");
        text3.setText(description);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


//        text3.setText(String.valueOf(person)+" Person");

    }
}
package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FacilitesView extends AppCompatActivity {
    private ImageView image;
    private TextView name , des;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilites_view);
        name = findViewById(R.id.getNameFes);
        image = findViewById(R.id.getImageFes);
        des = findViewById(R.id.getDecFes);

        Intent intent = getIntent();
        String gName =intent.getStringExtra("name");
        String gDes = intent.getStringExtra("dec");
        int gImage = intent.getIntExtra("image",0);

        name.setText(gName);
        image.setImageResource(gImage);
        des.setText(gDes);
    }
}
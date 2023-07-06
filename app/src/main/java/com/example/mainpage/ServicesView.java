package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ServicesView extends AppCompatActivity {
    private ImageView image;
    private TextView name;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_view);
        name = findViewById(R.id.getNameSer);
        image = findViewById(R.id.getImageSer);

        Intent intent = getIntent();
        String gName =intent.getStringExtra("name");
        String gDes = intent.getStringExtra("des");
        int gImage = intent.getIntExtra("image",0);

        name.setText(gName);
        image.setImageResource(gImage);
    }
}
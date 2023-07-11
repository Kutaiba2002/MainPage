package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mainpage.AboutHotel;
import com.example.mainpage.R;


public class HotelDetals extends AppCompatActivity {
    private ImageView img;
    private TextView texts1,texts2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detals);
        Intent intent = getIntent();
        int id = (int) intent.getExtras().get("hotel_id");

        AboutHotel hotels = AboutHotel.hotel[id];
        img = findViewById(R.id.imgs);
        img.setImageResource(hotels.getImg());

        texts1=findViewById(R.id.text1);
        texts2 = findViewById(R.id.text2);
        texts1.setText(hotels.getName());
        texts2.setText(hotels.getDes());
    }
}
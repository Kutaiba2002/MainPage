package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mainpage.AboutHotel;
import com.example.mainpage.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


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
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        Intent intent1 = getIntent();
        String docId = intent1.getStringExtra("docID");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle item clicks here
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.profile:
                        // Handle item 1 click
                        Intent intent = new Intent(HotelDetals.this, ProfileActivity.class);
                        intent.putExtra("docID",docId);
                        startActivity(intent);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(HotelDetals.this, MainActivity.class));

                        return true;
                    case R.id.settings:
                        logout();
                        return true;
                }
                return false;
            }
        });


    }
    private void logout() {
        startActivity(new Intent(HotelDetals.this, LogIn.class));
        finish();
    }
}
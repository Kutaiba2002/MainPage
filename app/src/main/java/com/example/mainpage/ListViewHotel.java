package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListViewHotel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_hotel);
        ArrayAdapter<AboutHotel> listAdapter = new ArrayAdapter<AboutHotel>(this,
                android.R.layout.simple_list_item_1,
                AboutHotel.hotel);

        ListView listView = (ListView)findViewById(R.id.lists);
        listView.setAdapter(listAdapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int pos,
                                    long id) {

                Intent intent = new Intent(ListViewHotel.this,
                        HotelDetals.class);
                intent.putExtra("hotel_id",pos);
                startActivity(intent);

            }
        };
        listView.setOnItemClickListener(itemClickListener);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        Intent intent = getIntent();
        String docId = intent.getStringExtra("docID");
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle item clicks here
                int itemId = item.getItemId();
                switch (itemId) {
                    case R.id.profile:
                        // Handle item 1 click
                        Intent intent = new Intent(ListViewHotel.this, ProfileActivity.class);
                        intent.putExtra("docID",docId);
                        startActivity(intent);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(ListViewHotel.this, MainActivity.class));

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
        startActivity(new Intent(ListViewHotel.this, LogIn.class));
        finish();
    }
}
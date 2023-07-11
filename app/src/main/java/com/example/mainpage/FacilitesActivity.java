package com.example.mainpage;

import static com.example.mainpage.R.id.recyclerViewIdFacilites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FacilitesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private ArrayList<Facilites> listitem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilites);
        recyclerView = findViewById(R.id.recyclerViewIdFacilites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((getApplicationContext()));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        Gson gson = new Gson();

        listitem = new ArrayList<>();
        Facilites item1 = new Facilites(R.drawable.airporttransfer, "Airport Transfer", "Airport transfer is a convenient service offered by hotels, providing transportation to and from the airport for their guests. It ensures a seamless and hassle-free journey to the hotel upon arrival and departure.");
        Facilites item2 = new Facilites(R.drawable.meetingservies, "Multilingual staff", "Multilingual staff in hotels are able to effectively communicate with guests in multiple languages, ensuring clear and efficient communication and providing assistance to international travelers. Their language skills contribute to a more personalized and comfortable stay for guests from diverse backgrounds.");
        Facilites item3 = new Facilites(R.drawable.box, "Safety Deposit Box", "Safety deposit boxes in hotels provide a secure place for guests to store their valuable belongings and important documents during their stay, offering peace of mind and added security.");
        Facilites item4 = new Facilites(R.drawable.touer, "Tour Desk", "The tour desk in hotels is a helpful resource where guests can obtain information about local attractions, book tours, and arrange transportation, making it convenient for guests to explore and experience the best of the destination during their stay.");
        Facilites item5 = new Facilites(R.drawable.car, "Car Rental Assistance", "Car rental assistance in hotels enables guests to easily arrange for car rentals through partnerships with local car rental companies, providing convenient transportation options and allowing guests to explore the area at their own pace and convenience.");
        Facilites item6 = new Facilites(R.drawable.currency, "Currency Exchange", "Currency exchange services in hotels allow guests to conveniently convert their money into the local currency, making it easier for international travelers to obtain the necessary funds for their expenses during their stay.");
        Facilites item7 = new Facilites(R.drawable.accessibility, "Accessibility Features", "Hotels with accessibility features ensure a comfortable stay for guests with disabilities, providing amenities such as wheelchair ramps, accessible bathrooms, and other accommodations to meet their specific needs. These features promote inclusivity and make the hotel experience accessible to all guests.");
        Facilites item8 = new Facilites(R.drawable.dog, "Pet-Friendly Amenities", "Pet-friendly amenities in hotels cater to guests traveling with pets, offering designated pet-friendly rooms, pet beds, and sometimes even pet-sitting services. These amenities allow guests to enjoy their stay while ensuring their furry companions are well taken care of.");

        listitem.add(item1);
        listitem.add(item2);
        listitem.add(item3);
        listitem.add(item4);
        listitem.add(item5);
        listitem.add(item6);
        listitem.add(item7);
        listitem.add(item8);



        String listAnimals = gson.toJson(listitem);
        editor.putString("123", listAnimals);
        editor.commit();

        FacilitesAdapter adapter = new FacilitesAdapter(this, listitem);
        recyclerView.setAdapter(adapter);
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
                        Intent intent = new Intent(FacilitesActivity.this, ProfileActivity.class);
                        intent.putExtra("docID",docId);
                        startActivity(intent);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(FacilitesActivity.this, MainActivity.class));

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
        startActivity(new Intent(FacilitesActivity.this, LogIn.class));
        finish();
    }

}
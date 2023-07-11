package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ServicesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private  ArrayList<Services> listitem ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((getApplicationContext()));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        Gson gson = new Gson();

        listitem = new ArrayList<>();
        Services item1 = new Services(R.drawable.foodservies,"Food services","The hotel's food service offers a variety of dining options, including on-site restaurants, buffets, room service, and specialized menus to meet different dietary preferences and needs. These services are available throughout the day and include all meals including breakfast, lunch, and dinner.");
        Services item2 = new Services(R.drawable.roomservies,"Room services","Room service brings the convenience of dining directly to guests' rooms, offering a wide selection of meals and beverages for them to enjoy without leaving their accommodation.");
        Services item3 = new Services(R.drawable.meetingservies,"Meeting and conference services","The hotel provides comprehensive meeting and conference services, offering well-equipped spaces and professional support to facilitate successful gatherings and events.");
        Services item4 = new Services(R.drawable.transferservice,"Transfer services","The hotel offers convenient transfer services, ensuring seamless transportation for guests to and from nearby airports, train stations, and other destinations.");
        Services item5 = new Services(R.drawable.toursandexcursionsservices,"Tours and excursions services","The hotel provides exciting tours and excursions, allowing guests to explore local attractions and experience the best of the destination with professional guidance and arrangements.");
        Services item6 = new Services(R.drawable.weddinghallservice,"Wedding hall service","The hotel offers exceptional wedding hall services, providing a beautiful venue and dedicated support to create memorable and seamless wedding celebrations.");
        Services item7 = new Services(R.drawable.luggagestorage,"Luggage storage service","The hotel provides convenient luggage storage services, allowing guests to securely store their belongings before check-in or after check-out.");
        Services item8 = new Services(R.drawable.laundryandironingservice,"Laundry and ironing service","The hotel provides convenient laundry and ironing services, allowing guests to have their clothes cleaned, washed, and ironed efficiently, ensuring a comfortable and presentable stay.");
        Services item9 = new Services(R.drawable.gymandspa,"Gyma nd spa service","The hotel offers both a state-of-the-art gym facility for fitness enthusiasts and a luxurious spa for guests to indulge in relaxation and rejuvenation during their stay.");
        Services item10 = new Services(R.drawable.childrenservice,"Children's service","The hotel provides excellent children's services, including dedicated play areas, engaging activities, and childcare options, ensuring a memorable and enjoyable stay for families with children.");

        listitem.add(item1);
        listitem.add(item2);
        listitem.add(item3);
        listitem.add(item4);
        listitem.add(item5);
        listitem.add(item6);
        listitem.add(item7);
        listitem.add(item8);
        listitem.add(item9);
        listitem.add(item10);


        String listAnimals = gson.toJson(listitem);
        editor.putString("123",listAnimals);
        editor.commit();

       ServicesAdapter adapter = new ServicesAdapter(this,listitem);
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
                        Intent intent = new Intent(ServicesActivity.this, ProfileActivity.class);
                        intent.putExtra("docID",docId);
                        startActivity(intent);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(ServicesActivity.this, MainActivity.class));

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
        startActivity(new Intent(ServicesActivity.this, LogIn.class));
        finish();
    }

}
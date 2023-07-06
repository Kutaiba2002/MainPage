package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

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
        Services item1 = new Services(R.drawable.foodservies,"Food services");
        Services item2 = new Services(R.drawable.roomservies,"Room services");
        Services item3 = new Services(R.drawable.meetingservies,"Meeting and conference services");
        Services item4 = new Services(R.drawable.transferservice,"Transfer services");
        Services item5 = new Services(R.drawable.toursandexcursionsservices,"Tours and excursions services");
        Services item6 = new Services(R.drawable.weddinghallservice,"Wedding hall service");
        Services item7 = new Services(R.drawable.luggagestorage,"Luggage storage service");
        Services item8 = new Services(R.drawable.laundryandironingservice,"Laundry and ironing service");
        Services item9 = new Services(R.drawable.gymandspa,"Gyma nd spa service");
        Services item10 = new Services(R.drawable.childrenservice,"children's service");

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
    }

}
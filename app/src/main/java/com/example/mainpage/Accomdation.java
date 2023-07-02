package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.google.android.material.search.SearchBar;

import java.util.ArrayList;

public class Accomdation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomdation);

        ArrayList<RoomDetails> roomArrayList = new ArrayList<>();
        roomArrayList.add(new RoomDetails(R.drawable.single_room,50,"Single Room",1));
        roomArrayList.add(new RoomDetails(R.drawable.double_bedroom,100,"Double Room",2));
        roomArrayList.add(new RoomDetails(R.drawable.suite,80,"Suite Room",3));
        roomArrayList.add(new RoomDetails(R.drawable.studio,70,"Studio Room",2));
        roomArrayList.add(new RoomDetails(R.drawable.connecting,150,"Connecting Room",4));
        roomArrayList.add(new RoomDetails(R.drawable.granda,120,"Granda Room",2));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        AccomdationAdapter accomdationAdapter = new AccomdationAdapter(roomArrayList);
        recyclerView.setAdapter(accomdationAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);


    }
}
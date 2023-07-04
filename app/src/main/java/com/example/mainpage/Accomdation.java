package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.search.SearchBar;

import java.util.ArrayList;

public class Accomdation extends AppCompatActivity implements RecyclerInterface {

    private ArrayList<RoomDetails> roomArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accomdation);

        roomArrayList = new ArrayList<>();
        roomArrayList.add(new RoomDetails(R.drawable.single_room,50,"Single Room",1,"A single room is designed to accommodate a single guest. It typically consists of a single bed and basic amenities."));
        roomArrayList.add(new RoomDetails(R.drawable.double_bedroom,100,"Double Room",2,"A double room is suitable for two guests and usually contains a double or queen-sized bed. It provides space and amenities for two people to stay comfortably."));
        roomArrayList.add(new RoomDetails(R.drawable.suite,80,"Suite Room",3,"A suite is a larger and more luxurious room option. It often includes a separate living area and bedroom. Suites may also feature additional amenities such as a kitchenette, dining area, or even a private balcony."));
        roomArrayList.add(new RoomDetails(R.drawable.studio,70,"Studio Room",2,"A studio room is an open-plan space that combines a living area, bedroom, and kitchenette in a single room. It is designed to offer a compact and self-contained accommodation option."));
        roomArrayList.add(new RoomDetails(R.drawable.connecting,150,"Connecting Room",4,"Connecting rooms are two separate rooms with a connecting door or corridor. They are convenient for families or groups who want to stay close to each other while having their own private space."));
        roomArrayList.add(new RoomDetails(R.drawable.granda,120,"Granda Room",2,"A grand suite is an upgraded and more spacious version of a regular suite. It offers enhanced amenities, additional living space, and often includes luxurious features like a private jacuzzi or panoramic views."));
        roomArrayList.add(new RoomDetails(R.drawable.adjacent,200,"Adjacent Room",5,"Adjacent rooms are rooms located next to each other but without a connecting door. They are suitable for guests who want to stay close to each other while maintaining separate accommodations."));
        roomArrayList.add(new RoomDetails(R.drawable.deluxe,150,"Deluxe Room",1,"A deluxe room is a higher-tier room option that offers superior comfort and amenities compared to standard rooms. It may feature upgraded furnishings, better views, and additional amenities like a minibar or a larger bathroom."));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        AccomdationAdapter accomdationAdapter = new AccomdationAdapter(roomArrayList, this);
        recyclerView.setAdapter(accomdationAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }
    @Override
    public void onItemClick(int position) {
        RoomDetails roomDetails = roomArrayList.get(position);
        Intent intent = new Intent(Accomdation.this, ShowDetails.class);
        intent.putExtra("name", roomDetails.getName());
        intent.putExtra("image", roomDetails.getImage());
        intent.putExtra("price", roomDetails.getPrice());
        intent.putExtra("description", roomDetails.getDescription());
        startActivity(intent);
    }
}
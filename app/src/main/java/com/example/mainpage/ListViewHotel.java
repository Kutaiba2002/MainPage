package com.example.mainpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    }
}
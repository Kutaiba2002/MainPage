package com.example.mainpage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccomdationAdapter extends RecyclerView.Adapter<AccomdationAdapter.ViewHolder> {

    private ArrayList<RoomDetails> roomArrayList = new ArrayList<>();

    public AccomdationAdapter(ArrayList<RoomDetails> roomArrayList) {
        this.roomArrayList = roomArrayList;
    }

    @NonNull
    @Override
    public AccomdationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.accomdation_cardview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AccomdationAdapter.ViewHolder holder, int position) {

        RoomDetails roomDetails = roomArrayList.get(position);
        holder.imageView.setImageResource(roomDetails.getImage());
        holder.priceTxt.setText(roomDetails.getPrice() + "$/day");
        holder.nameTxt.setText(roomDetails.getName());
        holder.numTxt.setText(String.valueOf(roomDetails.getNumberOfPerson() + " Person"));
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.recycler_animation));
    }

    @Override
    public int getItemCount() {
        return roomArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView priceTxt;
        public TextView nameTxt;
        public TextView numTxt;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.roomImage);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            nameTxt = itemView.findViewById(R.id.roomName);
            numTxt = itemView.findViewById(R.id.numPerson);
            cardView= itemView.findViewById(R.id.accomdation_cardView);
        }
    }
}

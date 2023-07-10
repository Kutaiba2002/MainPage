package com.example.mainpage;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class FacilitesAdapter extends RecyclerView.Adapter<FacilitesAdapter.ViewHolder> {
    private Context context;
    private List<Facilites> facilitesList;
    private SharedPreferences prefs;
    private Gson gson;

    public FacilitesAdapter(Context context, List<Facilites> facilitesList) {
        this.context = context;
        this.facilitesList = facilitesList;
        this.prefs = context.getSharedPreferences("123", Context.MODE_PRIVATE);
        this.gson = gson;
    }

    private void saveDataToSharedPreferences() {
        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(facilitesList);
        editor.putString("123", json);
        editor.apply();
    }

    @NonNull
    @Override
    public FacilitesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facilites_cardview, parent, false);
        return new FacilitesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilitesAdapter.ViewHolder holder, int position) {
        Facilites item = facilitesList.get(position);
        holder.name.setText(item.getName());
        holder.dec.setText(item.getDes());
        holder.image.setImageResource(item.getImage());
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.ress));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = holder.getAdapterPosition();

                Intent intent = new Intent(view.getContext(), FacilitesView.class);
                intent.putExtra("image", facilitesList.get(i).getImage());
                intent.putExtra("name", facilitesList.get(i).getName());
                intent.putExtra("dec",facilitesList.get(i).getDes());
                startActivity(view.getContext(), intent, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return facilitesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView image;
        private TextView name;
        private TextView dec;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.facilitesCardView);
            image = itemView.findViewById(R.id.facilitesImage);
            name = itemView.findViewById(R.id.txtFacilitesName);
            dec = itemView.findViewById(R.id.txtFacilitesDes);
        }
    }
}


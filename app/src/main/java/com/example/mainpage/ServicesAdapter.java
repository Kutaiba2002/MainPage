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

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
   private Context context;
   private List<Services> servicesList;
   private SharedPreferences prefs;
   private Gson gson;

    public ServicesAdapter(Context context, List<Services> servicesList) {
        this.context = context;
        this.servicesList = servicesList;
        this.prefs = context.getSharedPreferences("123", Context.MODE_PRIVATE);
        this.gson = gson;
    }

    private void saveDataToSharedPreferences() {
        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(servicesList);
        editor.putString("123", json);
        editor.apply();
    }

    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Services item = servicesList.get(position);
        holder.name.setText(item.getName());
        holder.image.setImageResource(item.getImage());
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.ress));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = holder.getAdapterPosition();

                Intent intent = new Intent(view.getContext(), ServicesView.class);
                intent.putExtra("image", servicesList.get(i).getImage());
                intent.putExtra("name", servicesList.get(i).getName());
                startActivity(view.getContext(), intent, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView image;
        private TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.services_cardView);
            image = itemView.findViewById(R.id.serviceImage);
            name = itemView.findViewById(R.id.txtServicesName);
        }
    }
}

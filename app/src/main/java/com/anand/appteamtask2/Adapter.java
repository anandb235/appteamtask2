package com.anand.appteamtask2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder>{

    Model res;
    public Adapter(Model res) {
        this.res = res;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_grid,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.foodName.setText(res.getResult().get(position).getFoodName());
        Glide.with(holder.itemView.getContext()).load(res.getResult().get(position)
                .getFoodImage()).into(holder.foodImage);
    }

    @Override
    public int getItemCount() {
        return res.getResult().size();
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        TextView foodName;
        ImageView foodImage;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodImage = itemView.findViewById(R.id.foodImage);
        }
    }
}
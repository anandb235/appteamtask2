package com.anand.appteamtask2;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.myViewHolder>{

    RandomModel res;
    public RandomAdapter(RandomModel res) {
        this.res = res;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_grid,parent,false);
        return new myViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.foodName.setText(res.getRecipes().get(position).getTitle());
        Glide.with(holder.itemView.getContext()).load(res.getRecipes().get(position)
                .getImage()).into(holder.foodImage);
        holder.id = res.getRecipes().get(position).getId();
        holder.prepTime.setText(String.format("Prep Time: %dmin", res.getRecipes().get(position).getReadyInMinutes()));
        holder.servings.setText(String.format("Servings: %d", res.getRecipes().get(position).getServings()));
        holder.estCost.setText(String.format("Est. Cost: %.2f", res.getRecipes().get(position).getPricePerServing()));
        holder.healthScore.setText(String.format("Health Score: %.1f", res.getRecipes().get(position).getHealthScore()));
        if (res.getRecipes().get(position).isVegetarian()){
            holder.vegInfo.setImageResource(R.drawable.ic_veg);
        }
        else{
            holder.vegInfo.setImageResource(R.drawable.ic_non_veg);
        }
    }

    @Override
    public int getItemCount() {
        return res.getRecipes().size();
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        TextView foodName,prepTime,servings,estCost,healthScore;
        ImageView foodImage,vegInfo;
        int id;
        CardView cardView;
        Button nextButton;
        RelativeLayout recipeInfo;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodImage = itemView.findViewById(R.id.foodImage);
            prepTime = itemView.findViewById(R.id.prepTime);
            servings = itemView.findViewById(R.id.servings);
            estCost = itemView.findViewById(R.id.cost);
            healthScore = itemView.findViewById(R.id.healthScore);
            cardView = itemView.findViewById(R.id.card);
            nextButton = itemView.findViewById(R.id.nextButton);
            recipeInfo = itemView.findViewById(R.id.recipeInfo);
            vegInfo = itemView.findViewById(R.id.veg_nonVeg);

            cardView.setOnClickListener(view -> {
                if (recipeInfo.getVisibility() == View.VISIBLE){
                    recipeInfo.setVisibility(View.GONE);
                }
                else {
                    recipeInfo.setVisibility(View.VISIBLE);
                }
            });

            nextButton.setOnClickListener(view -> {
                Toast.makeText(view.getContext(), "button "+id, Toast.LENGTH_SHORT).show();
            });
        }
    }
}
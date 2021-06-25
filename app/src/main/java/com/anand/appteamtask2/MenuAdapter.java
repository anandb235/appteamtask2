package com.anand.appteamtask2;

import android.annotation.SuppressLint;
import android.util.Log;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.myViewHolder>{

    MenuModel res;

    public MenuAdapter(MenuModel res) {
        this.res = res;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_grid,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.foodName.setText(res.getMenuItems().get(position).getTitle());
        Glide.with(holder.itemView.getContext()).load(res.getMenuItems().get(position)
                .getImage()).into(holder.foodImage);
        holder.restChain.setText(String.format("Chain: %s", res.getMenuItems().get(position).getRestaurantChain()));
        holder.id = res.getMenuItems().get(position).getId();

    }

    @Override
    public int getItemCount() {
        return res.getMenuItems().size();
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        TextView foodName,restChain,calories,protein,fat,carbs;
        ImageView foodImage;
        int id;
        CardView cardView;
        RelativeLayout relativeLayout;
        Button nextButton;




        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            foodName = itemView.findViewById(R.id.foodName);
            foodImage = itemView.findViewById(R.id.foodImage);
            restChain = itemView.findViewById(R.id.prepTime);
            cardView = itemView.findViewById(R.id.card);
            relativeLayout = itemView.findViewById(R.id.recipeInfo);
            nextButton = itemView.findViewById(R.id.nextButton);
            calories = itemView.findViewById(R.id.servings);
            fat = itemView.findViewById(R.id.cost);
            protein = itemView.findViewById(R.id.healthScore);
            carbs = itemView.findViewById(R.id.carbs);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.spoonacular.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            SpoonacularService search = retrofit.create(SpoonacularService.class);



            cardView.setOnClickListener(view -> {


                if (relativeLayout.getVisibility() == View.VISIBLE){
                    relativeLayout.setVisibility(View.GONE);
                }
                else{
                    search.getMenuItemInfo(id,BuildConfig.API_KEY).enqueue(new Callback<MenuInfoModel>(){

                        @SuppressLint("DefaultLocale")
                        @Override
                        public void onResponse(Call<MenuInfoModel> call, Response<MenuInfoModel> response) {
                            MenuInfoModel res = response.body();
                            calories.setText(String.format("Caloriessss: %.1f", res.getNutrition().get(0).getCalories()));
                            fat.setText(String.format("Fat: %s", res.getNutrition().get(0).getFat()));
                            protein.setText(String.format("Protein: %s", res.getNutrition().get(0).getProtein()));
                            carbs.setText(String.format("Carbs: %s", res.getNutrition().get(0).getCarbs()));
                        }

                        @Override
                        public void onFailure(Call<MenuInfoModel> call, Throwable t) {
                            Log.i("onFailure: ", t.getMessage());
                        }
                    });
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            });
            nextButton.setOnClickListener(view -> {
                Toast.makeText(view.getContext(), "button "+id, Toast.LENGTH_SHORT).show();
            });

        }
    }
}
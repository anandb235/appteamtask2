package com.anand.appteamtask2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SpoonacularService search = retrofit.create(SpoonacularService.class);
        search.getMenuItemInfo(res.getMenuItems().get(position).getId(),BuildConfig.API_KEY).enqueue(new Callback<MenuInfoResult>(){

            @SuppressWarnings("NullableProblems")
            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(Call<MenuInfoResult> call, Response<MenuInfoResult> response) {
                MenuInfoResult res = response.body();
                if (res!=null){
                    holder.calories.setText(String.format("Calories: %s", res.getNutrition().getCalories()));
                    holder.fat.setText(String.format("Fat: %s", res.getNutrition().getFat()));
                    holder.protein.setText(String.format("Protein: %s", res.getNutrition().getProtein()));
                    holder.carbs.setText(String.format("Carbs: %s", res.getNutrition().getCarbs()));
                }

            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call<MenuInfoResult> call, Throwable t) {
                Log.i("onFailure: ", t.getMessage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return res.getMenuItems().size();
    }


    static class myViewHolder extends RecyclerView.ViewHolder {
        TextView foodName,restChain,calories,protein,fat,carbs;
        ImageView foodImage;
        int id;
        CardView cardView;
        RelativeLayout relativeLayout;
        Button nextButton;
        Dialog myDialog;

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

            cardView.setOnClickListener(view -> {


                if (relativeLayout.getVisibility() == View.VISIBLE){
                    relativeLayout.setVisibility(View.GONE);
                }
                else{
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            });
            nextButton.setOnClickListener(view -> {
                myDialog = new Dialog(view.getContext());
                ShowPopup();
            });

        }
        public void ShowPopup(){
            TextView text;
            ImageView close,food;
            WebView webView;
            myDialog.setContentView(R.layout.custom_popup_menu);
            close = myDialog.findViewById(R.id.cutButton);
            food = myDialog.findViewById(R.id.foodImage);
            text = myDialog.findViewById(R.id.text);
            webView = myDialog.findViewById(R.id.webView);
            close.setOnClickListener(view -> myDialog.dismiss());
            food.setImageDrawable(foodImage.getDrawable());
            text.setText(foodName.getText().toString());
            webView.loadUrl("https://api.spoonacular.com/food/menuItems/"+id+"/nutritionWidget?defaultCss=true&apiKey="+BuildConfig.API_KEY);
            myDialog.show();
        }
    }
}
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

@SuppressWarnings("NullableProblems")
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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SpoonacularService search = retrofit.create(SpoonacularService.class);
        search.getRecipeCard(holder.id,BuildConfig.API_KEY).enqueue(new Callback<RecipeCard>(){

            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(Call<RecipeCard> call, Response<RecipeCard> response) {
                RecipeCard res = response.body();
//                Log.i("", String.valueOf(response.body()));
                if(res!=null)
                    holder.cardUrl = res.getUrl();
            }

            @Override
            public void onFailure(Call<RecipeCard> call, Throwable t) {
                Log.i("onFailure: ", t.getMessage());
            }
        });



    }

    @Override
    public int getItemCount() {
        return res.getRecipes().size();
    }


    static class myViewHolder extends RecyclerView.ViewHolder {
        TextView foodName,prepTime,servings,estCost,healthScore;
        ImageView foodImage,vegInfo;
        int id;
        CardView cardView;
        Button nextButton;
        RelativeLayout recipeInfo;
        Dialog myDialog;
        String cardUrl;
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
                myDialog = new Dialog(view.getContext());
                ShowPopup();
            });
        }
        public void ShowPopup(){
            ImageView close;
            WebView webView,foodCard;

            myDialog.setContentView(R.layout.custom_popup_recipe);
            close = myDialog.findViewById(R.id.cutButton);
            foodCard = myDialog.findViewById(R.id.foodImage);
            webView = myDialog.findViewById(R.id.webView);

            close.setOnClickListener(view -> myDialog.dismiss());
            foodCard.loadUrl(cardUrl);
            foodCard.getSettings().setLoadWithOverviewMode(true);
            foodCard.getSettings().setUseWideViewPort(true);
            webView.loadUrl("https://api.spoonacular.com/recipes/"+id+"/nutritionWidget?defaultCss=true&apiKey="+BuildConfig.API_KEY);
//              TextView viewLink = myDialog.findViewById(R.id.viewLink);
//            viewLink.setText(String.valueOf(id));
            myDialog.show();
        }
    }
}
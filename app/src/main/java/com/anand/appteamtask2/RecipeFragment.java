package com.anand.appteamtask2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@SuppressWarnings("NullableProblems")
public class RecipeFragment extends Fragment {
    RecyclerView recyclerView;
    SearchView searchBar;
    SpoonacularService search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_recipe, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchBar = v.findViewById(R.id.searchBar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        search = retrofit.create(SpoonacularService.class);
        if(searchBar.getQuery().toString().isEmpty()){
            randomRecipes();
        }

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String s) {
                search.getRecipe(s,true,BuildConfig.API_KEY).enqueue(new Callback<ComplexSearchModel>(){

                    @Override
                    public void onResponse(Call<ComplexSearchModel> call, Response<ComplexSearchModel> response) {
                        ComplexSearchModel res = response.body();
                        recyclerView.setAdapter(new SearchAdapter(res));
                    }

                    @Override
                    public void onFailure(Call<ComplexSearchModel> call, Throwable t) {
                        Log.i("onFailure: ", t.getMessage());
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }


        });
        return v;
    }

    private void randomRecipes() {
        search.getRandom(20,BuildConfig.API_KEY).enqueue(new Callback<RandomModel>(){

            @Override
            public void onResponse(Call<RandomModel> call, Response<RandomModel> response) {
                RandomModel res = response.body();
                recyclerView.setAdapter(new RandomAdapter(res));
            }

            @Override
            public void onFailure(Call<RandomModel> call, Throwable t) {
                Log.i("onFailure: ", t.getMessage());
            }
        });
    }
}
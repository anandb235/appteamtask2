package com.anand.appteamtask2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchMenuFragment extends Fragment {
    RecyclerView recyclerView;
    SpoonacularService search;
    String s;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_search_items, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        search = retrofit.create(SpoonacularService.class);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            s = bundle.getString("Query");
        }
        search.getMenuItem(s,15,BuildConfig.API_KEY).enqueue(new Callback<MenuModel>(){

            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {
                MenuModel res = response.body();
                recyclerView.setAdapter(new MenuAdapter(res));
            }

            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {
                Log.i("onFailure: ", t.getMessage());
            }
        });



        return v;
    }
}
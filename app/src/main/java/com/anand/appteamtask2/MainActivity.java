package com.anand.appteamtask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText searchBar;
    Model res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        searchBar = findViewById(R.id.searchBar);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ComplexSearch search = retrofit.create(ComplexSearch.class);
        search.getRecipe("pizza",BuildConfig.API_KEY).enqueue(new Callback<Model>(){

            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                res = response.body();
                recyclerView.setAdapter(new Adapter(res));
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.i("onFailure: ", t.getMessage());
            }
        });

    }
}
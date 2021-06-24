package com.anand.appteamtask2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ComplexSearch {
    @GET("recipes/complexSearch")
    Call<Model> getRecipe(@Query("query") String query, @Query("apiKey") String apiKey);
}

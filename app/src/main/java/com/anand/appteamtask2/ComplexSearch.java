package com.anand.appteamtask2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ComplexSearch {
    @GET("recipes/complexSearch")
    Call<ComplexSearchModel> getRecipe(@Query("query") String query, @Query("addRecipeInformation") boolean val
            ,@Query("apiKey") String apiKey);

    @GET("recipes/random")
    Call<RandomModel> getRandom(@Query("number") int number, @Query("apiKey") String apiKey);
}

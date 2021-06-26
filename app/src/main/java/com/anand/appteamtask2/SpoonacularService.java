package com.anand.appteamtask2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpoonacularService {
    @GET("recipes/complexSearch")
    Call<ComplexSearchModel> getRecipe(@Query("query") String query, @Query("addRecipeInformation") boolean val
            ,@Query("apiKey") String apiKey);

    @GET("recipes/random")
    Call<RandomModel> getRandom(@Query("number") int number, @Query("apiKey") String apiKey);

    @GET("food/menuItems/{id}")
    Call<MenuInfoResult> getMenuItemInfo(@Path("id") int id, @Query("apiKey") String apiKey);

    @GET("food/menuItems/search")
    Call<MenuModel> getMenuItem(@Query("query") String query, @Query("number") int number,@Query("apiKey") String apiKey);

    @GET("recipes/{id}/card")
    Call<RecipeCard> getRecipeCard(@Path("id") int id, @Query("apiKey") String apiKey);
}

package com.anand.appteamtask2;

import java.util.List;

public class RandomModel {
    List<ComplexSearchResults> recipes;

    public RandomModel(List<ComplexSearchResults> recipes) {
        this.recipes = recipes;
    }

    public List<ComplexSearchResults> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<ComplexSearchResults> recipes) {
        this.recipes = recipes;
    }
}

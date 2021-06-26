package com.anand.appteamtask2;

public class ComplexSearchResults {
    int id,readyInMinutes,servings;
    String title,image;
    float pricePerServing,healthScore;
    boolean vegetarian;

    public ComplexSearchResults(int id, int readyInMinutes, int servings, String title, String image, float pricePerServing, float healthScore, boolean vegetarian) {
        this.id = id;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.title = title;
        this.image = image;
        this.pricePerServing = pricePerServing;
        this.healthScore = healthScore;
        this.vegetarian = vegetarian;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }


    public int getServings() {
        return servings;
    }


    public String getTitle() {
        return title;
    }


    public String getImage() {
        return image;
    }


    public float getPricePerServing() {
        return pricePerServing;
    }



    public float getHealthScore() {
        return healthScore;
    }


    public boolean isVegetarian() {
        return vegetarian;
    }

}

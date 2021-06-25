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

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPricePerServing() {
        return pricePerServing;
    }

    public void setPricePerServing(float pricePerServing) {
        this.pricePerServing = pricePerServing;
    }

    public float getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(float healthScore) {
        this.healthScore = healthScore;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }
}

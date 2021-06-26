package com.anand.appteamtask2;
class Nutrition{
    public double calories;
    public String fat;
    public String protein;
    public String carbs;

    public Nutrition(double calories, String fat, String protein, String carbs) {
        this.calories = calories;
        this.fat = fat;
        this.protein = protein;
        this.carbs = carbs;
    }

    public double getCalories() {
        return calories;
    }

    public String getFat() {
        return fat;
    }

    public String getProtein() {
        return protein;
    }

    public String getCarbs() {
        return carbs;
    }
}
public class MenuInfoResult {
    public Nutrition nutrition;

    public MenuInfoResult(Nutrition nutrition) {
        this.nutrition = nutrition;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

}

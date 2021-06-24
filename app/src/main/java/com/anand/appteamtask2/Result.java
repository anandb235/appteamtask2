package com.anand.appteamtask2;

public class Result{
    String foodName,foodImage,imageType;
    int foodId;
    //"id":654959,"title":"Pasta With Tuna","image":"https://spoonacular.com/recipeImages/654959-312x231.jpg","imageType":"jpg"

    public Result(String foodName, String foodImage, int foodId, String imageType) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodId = foodId;
        this.imageType = imageType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}

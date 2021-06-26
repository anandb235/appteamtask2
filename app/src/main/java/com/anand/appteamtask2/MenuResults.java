package com.anand.appteamtask2;

public class MenuResults {
    int id;
    String title,restaurantChain,image;

    public MenuResults(int id, String title, String restaurantChain, String image) {
        this.id = id;
        this.title = title;
        this.restaurantChain = restaurantChain;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getRestaurantChain() {
        return restaurantChain;
    }

    public String getImage() {
        return image;
    }
}
